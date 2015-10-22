package controller.forms.post;

import io.vertx.core.Handler;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.Session;
import model.entity.Room;
import model.entity.User;
import service.RoomService;
import service.UserService;
import service.factory.ServiceFactory;

/**
 * Created by Vitaly on 21.10.15.
 */
public class CreateRoomHandler implements Handler<RoutingContext> {

    private RoomService roomService = ServiceFactory.getRoomService();
    private UserService userService = ServiceFactory.getUserService();

    @Override
    public void handle(RoutingContext routingContext) {
        String linkValue = routingContext.request().getFormAttribute("linkValue");
        Session session = routingContext.session();

        User user = new User(session.id());
        Room room = new Room(linkValue);
        room.addUser(user);

        userService.addUser(user);
        roomService.addRoom(room);

        routingContext.response().setStatusCode(301);
        routingContext.response().putHeader("Location", "/room/gg");
        routingContext.response().end();
    }
}
