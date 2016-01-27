/**
 * Created by vitaly on 11.08.15.
 */

import controller.WebSocketHandler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.auth.oauth2.AccessToken;
import io.vertx.rxjava.ext.auth.oauth2.OAuth2Auth;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.OAuth2AuthHandler;

import java.util.LinkedHashSet;

public class RxJavaExample {

//    public static User user = null;
//    public static Room room = null;

    static String code = "";
    static String redirect_uri = "";

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();
        Router router = Router.router(vertx);
        HttpServer server = vertx.createHttpServer();
        OAuth2Auth authProvider = OAuth2Auth.create(vertx, OAuth2FlowType.AUTH_CODE, new JsonObject()
                .put("clientID", "229238477410510")
                .put("clientSecret", "dea6a18035af28f86e0368db46f4679b")
                .put("site", "https://www.facebook.com")
                .put("authorizationPath", "/dialog/oauth")
                .put("tokenPath", "https://graph.facebook.com/oauth/access_token"));

// create a oauth2 handler on our domain: "http://localhost:8080"
        OAuth2AuthHandler oauth2 = OAuth2AuthHandler.create(authProvider, "http://myworknow.tk/");

// these are the scopes
        oauth2.addAuthority("public_profile");

// setup the callback handler for receiving the Google callback

        router.route("/callback").handler(ctx->{
            code = ctx.request().getParam("code");
            System.out.println("code: " + code);

            redirect_uri = ctx.request().getParam("redirect_uri");
            System.out.println("redirect_uri: " + redirect_uri);

            ctx.response().setStatusCode(301);
            ctx.response().putHeader("Location", redirect_uri);
            ctx.response().end();
        });
        oauth2.setupCallback(router.get("/callback"));
        JsonObject tokenConfig = new JsonObject()
                .put("code", code)
                .put("redirect_uri", redirect_uri);

        authProvider.getToken(tokenConfig, res -> {
                    if (res.failed()) {
                        System.err.println("Access Token Error: " + res.cause().getMessage());
                    } else {
                        // Get the access token object (the authorization code is given from the previous step).
                        AccessToken token = res.result();
                        System.out.println("Access Token success: " + token);
                    }
                });
// protect everything under /protected
        router.route("/protected/*").handler(oauth2);
// mount some handler under the protected zone
        router.route("/protected/somepage").handler(rc -> {
            rc.response().end("Welcome to the protected resource!");
        });

// welcome page
        router.get("/").handler(ctx -> {
            ctx.response().putHeader("content-type", "text/html").end("Hello<br><a href=\"/protected/somepage\">Protected by FaceBook</a>");
        });
        server.requestStream().toObservable().subscribe(router::accept);

        server.websocketHandler(new WebSocketHandler());
        server.listenObservable(8080);
    }
}
