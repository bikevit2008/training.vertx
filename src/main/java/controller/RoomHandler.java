package controller;

import de.neuland.jade4j.Jade4J;
import io.vertx.core.Handler;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.Session;
import model.entity.Room;
import model.entity.User;
import service.RoomService;
import service.UserService;
import service.factory.ServiceFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by denis on 13/10/15.
 */
public class RoomHandler implements Handler<RoutingContext> {

    private RoomService roomService = ServiceFactory.getRoomService();
    private UserService userService = ServiceFactory.getUserService();

    @Override
    public void handle(RoutingContext routingContext) {
        String roomUrl = routingContext.request().getParam("roomUri");
        HttpServerResponse resp = routingContext.response();
        resp.putHeader("content-type", "text/html");
        Map<String, Object> model = new HashMap<String, Object>();

        String sessionId = routingContext.getCookie("vertx-web.session").getValue();
        System.out.println("Session ID from server: " + sessionId);
        //Room room = roomService.getRoomByUrl(roomUrl);
        User user = new User(sessionId);
        //room.addUser(user);
        //roomService.updateRoom(room);
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
