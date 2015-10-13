package server;

import controller.HomeHandler;
import controller.RoomHandler;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.web.Route;
import io.vertx.rxjava.ext.web.Router;

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

        Route homePage = router.route().path("/");
        homePage.handler(new HomeHandler());

        Route roomPage = router.route().path("/room/");
        roomPage.handler(new RoomHandler());

        System.out.println("Listen 8080 port ...");
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }
}
