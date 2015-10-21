package controller.forms.post;

import io.vertx.core.Handler;
import io.vertx.rxjava.ext.web.RoutingContext;

/**
 * Created by Vitaly on 21.10.15.
 */
public class CreateRoomHandler implements Handler<RoutingContext> {
        @Override
        public void handle(RoutingContext routingContext) {
                System.out.println("Link value: " + routingContext.request().getFormAttribute("linkValue"));
                routingContext.response().setStatusCode(301);
                routingContext.response().putHeader("Location", "/room/gg");
                routingContext.response().end();
        }
    }
