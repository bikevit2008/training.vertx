package controller;

import de.neuland.jade4j.Jade4J;
import io.vertx.core.Handler;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.RoutingContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vitaly on 30.10.15.
 */
public class FailureHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerResponse resp = routingContext.response();
        resp.putHeader("content-type", "text/html");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("validationLink", "validLink");
        model.put("displayView", "displayNone");

        // Write to the response and end it
        try {
            String html = Jade4J.render("web/templates/errorPage.jade", model);

            resp.end(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
