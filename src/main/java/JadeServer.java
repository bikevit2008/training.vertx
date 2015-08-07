import de.neuland.jade4j.Jade4J;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by vitaly on 07.08.15.
 */
public class JadeServer {
    public static void main(String[] args) {


        Vertx vertx = null;
        Router router = Router.router(vertx);

        // Entry point to the application, this will render a custom template.

        router.route().handler(routingContext -> {
                // we define a hardcoded title for our application

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("name", "Bob.x");

            // This handler will be called for every request
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/html");

            // Write to the response and end it
            try {
                String html = Jade4J.render("web/templates/index.jade", model);

                response.end(html);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


        // start a HTTP web server on port 8080
        Vertx.vertx().createHttpServer().requestHandler(router::accept).listen(8080);
    }
}
