package controller;

import io.vertx.core.Handler;
import io.vertx.rxjava.ext.web.RoutingContext;
import utils.RoutingContextAutomator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by denis on 13/10/15.
 */
public class HomeHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext routingContext) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("validationLink", "validLink");
        model.put("displayView", "displayNone");
        RoutingContextAutomator.globalHandle(routingContext, model, "home/landing", routingContext.response());
    }
}