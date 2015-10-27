package controller;

import io.vertx.core.Handler;
import io.vertx.rxjava.core.http.ServerWebSocket;
import model.entity.Message;
import model.entity.Room;
import model.entity.User;
import server.MainServer;
import service.RoomService;
import service.UserService;
import service.factory.ServiceFactory;

/**
 * Created by Vitaly on 25.10.15.
 */
public class WebSocketHandler implements Handler<ServerWebSocket> {


    private UserService userService = ServiceFactory.getUserService();
    private RoomService roomService = ServiceFactory.getRoomService();


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
        room.addUser(serverWebSocket.textHandlerID());

        Message message = new Message(1, "Hi!", "Vitaly");
        room.addMessage(message);
        room.setCountUsers(room.getCountUsers() + 1);
        roomService.updateRoom(room);


        System.out.println(room.getCountUsers());

        for (String textHandlerID : room.getUsers()){
            MainServer.eb.publish(textHandlerID, JSONHandler.convertToJSON(room.getCountUsers()));
        }

        //When client disconnect*/
        serverWebSocket.closeHandler(handler -> {
            /*User->1.Session
            *       2.TextHandlerId
            *       3.Nickname */
            //Here we must delete TextHandlerID
            room.setCountUsers(room.getCountUsers() - 1);
            room.removeUser(serverWebSocket.textHandlerID());
            roomService.updateRoom(room);

            System.out.println(room.getCountUsers());
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
