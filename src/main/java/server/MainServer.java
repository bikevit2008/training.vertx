package server;

import controller.WebSocket.WebSocketHandler;
import controller.http.site.*;
import controller.http.site.forms.post.*;
import controller.http.embed.*;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Route;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.*;
import io.vertx.rxjava.ext.web.sstore.LocalSessionStore;
import utils.LangParser;

import java.util.HashMap;

/**
 * Created by denis on 13/10/15.
 */
public class MainServer {

    public static void main(String[] args) {
        new MainServer().start();
    }

    public static Vertx vertx = Vertx.vertx();
    public static EventBus eb = vertx.eventBus();
    public static final HashMap<String, HashMap<String, Object>> languages = LangParser.getLanguages();

    public void start() {

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

        router.route().handler(CookieHandler.create());

        Route homePage = router.route().path("/");
        homePage.handler(new HomeHandler());

        Route roomPage = router.route().path("/room/:roomUri/*");
        roomPage.handler(new RoomHandler());


        Route iframeRoomPage = router.route().path("/embed/room/:roomUri/*");
        iframeRoomPage.handler(new IframeRoomHandler());

        Route resources = router.route().path("/resources/*");
        resources.handler(StaticHandler.create().setWebRoot("web/static/resources"));

        router.post("/createRoom").handler(new CreateRoomHandler());

        router.route().handler(FaviconHandler.create("web/static/logo.ico"));

        router.route().failureHandler(new FailureHandler());



        System.out.println("Listen 8080 port ...");
        HttpServer server = vertx.createHttpServer();

        server.websocketHandler(new WebSocketHandler());

        server.requestHandler(router::accept).listen(8080);
    }
}
