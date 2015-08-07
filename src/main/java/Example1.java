import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * Created by vitaly on 07.08.15.
 */
public class Example1 {
    public static void main(String[] args) {
        Vertx vertx = null;
        HttpServer server = Vertx.vertx().createHttpServer();

        Router router = Router.router(vertx);
        Route route = router.route().path("/some/path/");

        route.handler(routingContext -> {
            // This handler will be called for the following request paths:

            // `/some/path`
            // `/some/path/`
            // `/some/path//`
            //
            // but not:
            // `/some/path/subdir`

            // This handler will be called for every request
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");

            // Write to the response and end it
            response.end("Hello World from Vert.x-Web! Some path, Simple REST");
        });
        router.route().handler(routingContext -> {

            // This handler will be called for every request
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");

            // Write to the response and end it
            response.end("Hello World from Vert.x-Web!");
        });

        server.requestHandler(router::accept).listen(8080);
    }
}
