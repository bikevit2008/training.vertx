import de.neuland.jade4j.Jade4J;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by vitaly on 08.08.15.
 */
public class WebSocketExample {
    public static void main(String[] args) {
        HttpServer server = Vertx.vertx().createHttpServer();
        Vertx vertx = null;
        Router router = Router.router(vertx);

        router.route().handler(routingContext -> {
            // we define a hardcoded title for our application

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("name", "Bob.x");

            // This handler will be called for every request
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/html");

            // Write to the response and end it
            try {
                String html = Jade4J.render("web/templates/websocket.jade", model);

                response.end(html);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        server
                .websocketHandler(serverWebSocket -> {
                    serverWebSocket.writeFrame(io.vertx.core.http.WebSocketFrame.textFrame("Hi!", true));
                })
                .requestHandler(router::accept)
                .listen(8080);
    }
}