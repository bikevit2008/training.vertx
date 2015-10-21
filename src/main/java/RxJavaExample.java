/**
 * Created by vitaly on 11.08.15.
 */

import de.neuland.jade4j.Jade4J;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.Route;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.StaticHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class RxJavaExample {
    public static LinkedHashSet<String> room = new LinkedHashSet<String>();

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);
        Route home = router.route().path("/");
        home.handler(routingContext -> {
            HttpServerResponse resp = routingContext.response();
            resp.putHeader("content-type", "text/html");
            Map<String, Object> model = new HashMap<String, Object>();

            // Write to the response and end it
            try {
                String html = Jade4J.render("web/templates/landing.jade", model);

                resp.end(html);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Route routeRoom = router.route().path("/room/:roomUri/*");
        Route resources = router.route().path("/*");

        resources.handler(StaticHandler.create().setWebRoot("web/static"));


        routeRoom.handler(routingContext -> {
            String roomUri = routingContext.request().getParam("roomUri");
            System.out.println(roomUri);
            HttpServerResponse resp = routingContext.response();
            resp.putHeader("content-type", "text/html");
            Map<String, Object> model = new HashMap<String, Object>();

            // Write to the response and end it
            try {
                String html = Jade4J.render("web/templates/room.jade", model);

                resp.end(html);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        router.route().handler(routingContext -> {
            routingContext.response().putHeader("content-type", "text/html").end("Not Found!");
        });

        HttpServer server = vertx.createHttpServer();
    /*HttpServerResponse resp = req.response();
            resp.putHeader("content-type", "text/html");
            Map<String, Object> model = new HashMap<String, Object>();

            // Write to the response and end it
            try {
                String html = Jade4J.render("web/templates/websocket.jade", model);

                resp.end(html);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        server.requestStream().toObservable().subscribe(router::accept);

        EventBus eb = vertx.eventBus();

        server.websocketStream().toObservable().subscribe(
                socket -> {
                    socket.toObservable().subscribe(buffer -> {
                        System.out.println("Got message " + buffer.toString("UTF-8"));
                        room.add(socket.textHandlerID());
                        System.out.println(socket.textHandlerID());
                        for (String client : room) {
                            eb.publish(client, buffer.toString("UTF-8"));
                        }
                    });

                },
                failure -> System.out.println("Should never be called"),
                () -> {
                    System.out.println("Subscription ended or server closed");
                }
        );
        server.listenObservable(8080);
    }
}
