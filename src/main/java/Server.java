/**
 * Created by vitaly on 07.08.15.
 */
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class Server {

 public static void main(String[] args) {
     Vertx vertx = null;
     /*
     Router router = Router.router(vertx);

     router.route().handler(routingContext -> {
         routingContext.response().putHeader("content-type", "text/html").end("Hello Worldddd!");
     });
     // Create an HTTP server which simply returns "Hello World!" to each request.
    Vertx
            .vertx()
            .createHttpServer()
            .requestHandler(router::accept)
            .listen(8080);
     */
     HttpServer server = Vertx.vertx().createHttpServer();

     Router router = Router.router(vertx);

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