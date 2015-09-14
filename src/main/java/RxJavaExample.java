/**
 * Created by vitaly on 11.08.15.
 */
import de.neuland.jade4j.Jade4J;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.core.http.HttpServerResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class RxJavaExample {
    public static String roomName = "123";
    public static void main(String[] args) {
        Rooms.addNew(roomName);
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        server.requestStream().toObservable().subscribe(req -> {
            HttpServerResponse resp = req.response();
            resp.putHeader("content-type", "text/html");
            Map<String, Object> model = new HashMap<String, Object>();

            // Write to the response and end it
            try {
                String html = Jade4J.render("web/templates/websocket.jade", model);

                resp.end(html);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        EventBus eb = vertx.eventBus();

        server.websocketStream().toObservable().subscribe(
                socket -> {
                    socket.toObservable().subscribe(buffer -> {
                        Room.addClient(roomName, socket.textHandlerID());
                        System.out.println("Got message " + buffer.toString("UTF-8"));
                        LinkedHashSet<String> room = Rooms.get(roomName);
                        for(String client:room){
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
