package controller;

import io.vertx.core.Handler;
import io.vertx.rxjava.core.http.ServerWebSocket;
import model.entity.Message;
import model.entity.Room;
import model.entity.User;
import server.MainServer;
import service.IdsService;
import service.RoomService;
import service.UserService;
import service.factory.ServiceFactory;

import java.util.ArrayList;

/**
 * Created by Vitaly on 25.10.15.
 */
public class WebSocketHandler implements Handler<ServerWebSocket> {


    private UserService userService = ServiceFactory.getUserService();
    private RoomService roomService = ServiceFactory.getRoomService();
    private IdsService idsService = ServiceFactory.getIdsService();


    @Override
    public void handle(ServerWebSocket serverWebSocket) {

        String roomUrl = parseRoomUrlWS(serverWebSocket.path());
        Room room = roomService.getRoomByUrl(roomUrl);
        // onplay.me/websocket/room/dfsdfsdfsdfsf

        String sessionId = parseCookieWS(serverWebSocket.headers().get("Cookie"));
        System.out.println("Session: " + sessionId);
        User user = userService.getUserById(sessionId);
        user.setTextHandlerid(serverWebSocket.textHandlerID());
        userService.updateUser(user);

        Message message = new Message(1, "Hi!", "Vitaly");
        room.addMessage(message);

        ArrayList<String> idsRoom = idsService.getRoomByUrl(roomUrl);
        idsRoom.add(serverWebSocket.textHandlerID());
        idsService.updateRoom(roomUrl, idsRoom);

        room.countUsers.setCountUsers(idsRoom.size());
        roomService.updateRoom(room);
        System.out.println(room.countUsers.getCountUsers());


        for (String textHandlerID : idsRoom){
            MainServer.eb.publish(textHandlerID, JSONHandler.convertToJSON(room.countUsers));
            MainServer.eb.publish(textHandlerID, JSONHandler.convertToJSON(room.playStatusWork));
            MainServer.eb.publish(textHandlerID, JSONHandler.convertToJSON(room.time));
            MainServer.eb.publish(textHandlerID, JSONHandler.convertToJSON(room.messages));
            MainServer.eb.publish(textHandlerID, JSONHandler.convertToJSON(room.messages.get(0)));
            MainServer.eb.publish(textHandlerID, JSONHandler.convertToJSON(room));
        }

        //When client disconnect*/
        serverWebSocket.closeHandler(handler -> {

            idsRoom.remove(serverWebSocket.textHandlerID());
            idsService.updateRoom(roomUrl, idsRoom);

            room.countUsers.setCountUsers(idsRoom.size());
            roomService.updateRoom(room);

            for (String textHandlerID : idsRoom){
                MainServer.eb.publish(textHandlerID, JSONHandler.convertToJSON(room.countUsers));
            }
            System.out.println(room.countUsers.getCountUsers());
            System.out.println("Client: " + serverWebSocket.textHandlerID() + " Disconnected.");

        });

        //Send and get messages from(to) client
        serverWebSocket.toObservable().subscribe(buffer -> {

            System.out.println("RoomUrl: " + roomUrl);
            System.out.println("Got message " + buffer.toString("UTF-8"));
            MainServer.eb.publish(serverWebSocket.textHandlerID(), buffer.toString("UTF-8"));
            System.out.println(serverWebSocket.textHandlerID());

        });
    }

    private String parseRoomUrlWS(String path){
        return path.substring(16);
    }

    private static String parseCookieWS(String cookie){
        String[] split = cookie.split("vertx-web.session=");
        String[] splitDot = split[1].split(";");
        return  splitDot[0];
    }
}
