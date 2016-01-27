package controller;

import de.neuland.jade4j.Jade4J;
import io.vertx.core.Handler;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.RoutingContext;
import utils.JadeEngine;
import utils.RoutingContextAutomator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vitaly on 30.10.15.
 */
public class FailureHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext routingContext) {
        //HttpServerResponse response = routingContext.response();
        //response.setStatusCode(404);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("validationLink", "validLink");
        model.put("displayView", "displayNone");
        RoutingContextAutomator.globalHandle(routingContext, model, "error/errorPage", routingContext.response());

    }
}
