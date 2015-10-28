package controller;

import de.neuland.jade4j.Jade4J;
import io.vertx.core.Handler;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.RoutingContext;
import model.entity.User;
import service.IdsService;
import service.UserService;
import service.factory.ServiceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by denis on 13/10/15.
 */
public class RoomHandler implements Handler<RoutingContext> {

    private UserService userService = ServiceFactory.getUserService();
    private IdsService idsService = ServiceFactory.getIdsService();


    @Override
    public void handle(RoutingContext routingContext) {
        String roomUrl = routingContext.request().getParam("roomUri");
        HttpServerResponse resp = routingContext.response();
        resp.putHeader("content-type", "text/html");
        Map<String, Object> model = new HashMap<String, Object>();

        String sessionId = routingContext.getCookie("vertx-web.session").getValue();
        System.out.println("Session ID from server: " + sessionId);

        ArrayList<String> idsRoom = new ArrayList<String>();
        idsService.addRoom(roomUrl,idsRoom);

        User user = new User(sessionId);
        userService.addUser(user);



        // Write to the response and end it
        try {
            String html = Jade4J.render("web/templates/room.jade", model);

            resp.end(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
