package server;

import controller.HomeHandler;
import controller.RoomHandler;
import controller.forms.post.CreateRoomHandler;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Route;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.BodyHandler;
import io.vertx.rxjava.ext.web.handler.SessionHandler;
import io.vertx.rxjava.ext.web.handler.StaticHandler;
import io.vertx.rxjava.ext.web.sstore.LocalSessionStore;

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

        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

        Route homePage = router.route().path("/");
        homePage.handler(new HomeHandler());

        Route roomPage = router.route().path("/room/:roomUri/*");
        roomPage.handler(new RoomHandler());

        Route resources = router.route().path("/*");
        resources.handler(StaticHandler.create().setWebRoot("web/static"));

        router.post("/createRoom").handler(new CreateRoomHandler());



        System.out.println("Listen 8080 port ...");
        HttpServer server = vertx.createHttpServer();

        server.websocketStream().toObservable().subscribe(
                socket -> {

                    socket.closeHandler(handler -> {

                       System.out.println("Client: " + socket.textHandlerID() + " Disconnected.");

                    });

                    socket.toObservable().subscribe(buffer -> {
                        System.out.println("Got message " + buffer.toString("UTF-8"));
                        //         room.add(socket.textHandlerID());
                        System.out.println(socket.textHandlerID());
                    });

                },
                failure -> System.out.println("Should never be called"),
                () -> {
                    System.out.println("Subscription ended or server closed");
                });

        server.requestHandler(router::accept).listen(8080);
    }
}
