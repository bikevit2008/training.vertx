package controller;

import de.neuland.jade4j.Jade4J;
import io.vertx.core.Handler;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.RoutingContext;
import model.entity.Room;
import model.entity.User;
import model.entity.WSUser;
import service.IdsService;
import service.RoomService;
import service.UserService;
import service.factory.ServiceFactory;
import utils.JadeEngine;
import utils.RoutingContextAutomator;

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
    private RoomService roomService = ServiceFactory.getRoomService();


    @Override
    public void handle(RoutingContext routingContext) {
        String roomUrl = routingContext.request().getParam("roomUri");
        Room room = roomService.getRoomByUrl(roomUrl);
        String videoId = room.getVideoId();

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("videoId", videoId);
        model.put("messages", room.getMessages());

        String sessionId = routingContext.getCookie("vertx-web.session").getValue();
        System.out.println("Session ID from server: " + sessionId);

        ArrayList<WSUser> idsRoom = new ArrayList<WSUser>();
        idsService.addRoom(roomUrl,idsRoom);

        User user = new User(sessionId);
        userService.addUser(user);

        RoutingContextAutomator.globalHandle(routingContext, model, "room/room", routingContext.response());
    }
}
