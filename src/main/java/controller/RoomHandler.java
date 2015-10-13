package controller;

import io.vertx.core.Handler;
import io.vertx.rxjava.ext.web.RoutingContext;

/**
 * Created by denis on 13/10/15.
 */
public class RoomHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext routingContext) {
        routingContext.response().putHeader("content-type", "text/html").end("Hello Room Page!");
    }
}
