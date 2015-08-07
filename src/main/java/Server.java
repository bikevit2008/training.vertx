/**
 * Created by vitaly on 07.08.15.
 */
import io.vertx.core.Vertx;

public class Server {

 public static void main(String[] args) {
    // Create an HTTP server which simply returns "Hello World!" to each request.
    Vertx.vertx().createHttpServer().requestHandler(req -> req.response().end("Hello World!")).listen(8080);
  }
}