package server;

import controller.HomeHandler;
import controller.RoomHandler;
import controller.forms.post.CreateRoomHandler;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.web.Route;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.BodyHandler;
import io.vertx.rxjava.ext.web.handler.StaticHandler;

/**
 * Created by denis on 13/10/15.
 */
public class MainServer {

    public static void main(String[] args) {
        new MainServer().start();
    }

    public void start() {

        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

        Route homePage = router.route().path("/");
        homePage.handler(new HomeHandler());

        Route roomPage = router.route().path("/room/:roomUri/*");
        roomPage.handler(new RoomHandler());

        Route resources = router.route().path("/*");
        resources.handler(StaticHandler.create().setWebRoot("web/static"));

        router.post("/createRoom").handler(new CreateRoomHandler());

        System.out.println("Listen 8080 port ...");
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }
}
