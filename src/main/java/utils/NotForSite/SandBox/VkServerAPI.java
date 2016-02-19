package utils.NotForSite.SandBox; /**
 * Created by vitaly on 11.08.15.
 */

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.auth.oauth2.AccessToken;
import io.vertx.rxjava.ext.auth.oauth2.OAuth2Auth;
import io.vertx.rxjava.ext.web.Router;

public class VkServerAPI {



    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();
        Router router = Router.router(vertx);
        HttpServer server = vertx.createHttpServer();

        JsonObject credentials = new JsonObject()
                .put("clientID", "5248058")
                .put("clientSecret", "GApRl40vdm6QyzaeDZoH")
                .put("site", "https://oauth.vk.com")
                .put("authorizationPath", "/authorize")
                .put("tokenPath", "/access_token");


// Initialize the OAuth2 Library
        OAuth2Auth oauth2 = OAuth2Auth.create(vertx, OAuth2FlowType.AUTH_CODE, credentials);

// Authorization oauth2 URI
        String authorization_uri = oauth2.authorizeURL(new JsonObject()
                .put("redirect_uri", "http://myworknow.tk/return")
                .put("scope", "video")
                .put("state", ""));

// Redirect example using Vert.x
        router.route("/").handler(ctx -> {
            ctx.response().putHeader("Location", authorization_uri)
                            .setStatusCode(302)
                            .end();
                });
        router.route("/return").handler(ctx -> {
            String code = ctx.request().getParam("code");
            System.out.println("code: "+ code);
            ctx.response().end();
            oauth2.getToken(new JsonObject().put("code", code).put("redirect_uri", "http://myworknow.tk/return"), res -> {
                if (res.failed()) {
                    // error, the code provided is not valid
                    System.out.println("Access token not succesful: " + res.result());
                } else {
                    AccessToken accessToken = res.result();

                    oauth2.api(HttpMethod.POST, "https://api.vk.com/method/video.get", new JsonObject().put("video", "55188878_172769754"), handler -> {
                        System.out.println("Json result object: "+handler.result());
                    });
                    System.out.println("Access token succesful: " + res.result());
                }
            });
        });


        server.requestStream().toObservable().subscribe(router::accept);

        server.listenObservable(8080);
    }
}
