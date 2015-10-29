package controller;

import io.vertx.core.Handler;
import io.vertx.rxjava.core.http.ServerWebSocket;
import model.entity.GotJSON;
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
        ArrayList<String> idsRoom = idsService.getRoomByUrl(roomUrl);
        String textHandlerID = serverWebSocket.textHandlerID();
        // onplay.me/websocket/room/dfsdfsdfsdfsf

        String sessionId = parseCookieWS(serverWebSocket.headers().get("Cookie"));
        System.out.println("Session: " + sessionId);
        User user = userService.getUserById(sessionId);
        userService.updateUser(user);

        idsRoom.add(serverWebSocket.textHandlerID());
        idsService.updateRoom(roomUrl, idsRoom);

        room.countUsers.setCountUsers(idsRoom.size());
        roomService.updateRoom(room);
        System.out.println(room.countUsers.getCountUsers());

        //Send new countUsers to all
        for (String textHandlerIDs : idsRoom) {
            MainServer.eb.publish(textHandlerIDs, JSONHandler.convertToJSON(room.countUsers));
        }
        //When client connected
        MainServer.eb.publish(textHandlerID, JSONHandler.convertToJSON(room.playStatusWork));
        MainServer.eb.publish(textHandlerID, JSONHandler.convertToJSON(room.time));
        //Messages inserted to html

        //When client disconnect*/
        serverWebSocket.closeHandler(handler -> {

            idsRoom.remove(serverWebSocket.textHandlerID());
            idsService.updateRoom(roomUrl, idsRoom);

            room.countUsers.setCountUsers(idsRoom.size());
            roomService.updateRoom(room);

            //Send new countUsers to all
            for (String textHandlerIDs : idsRoom){
                MainServer.eb.publish(textHandlerIDs, JSONHandler.convertToJSON(room.countUsers));
            }
            System.out.println(room.countUsers.getCountUsers());
            System.out.println("Client: " + serverWebSocket.textHandlerID() + " Disconnected.");

        });
        serverWebSocket.frameHandler(handler -> {
            System.out.println("Got message: " + handler.textData());
            GotJSON gotJSON = JSONHandler.convertFromJSON(handler.textData());



            if(gotJSON.getNickName() != null){
                user.setNickName(gotJSON.getNickName());
                userService.updateUser(user);
            }

            if (gotJSON.getMessage() != null) {
                Message message = new Message(gotJSON.getMessage(), user.getNickName());
                for (String textHandlerIDs : idsRoom) {
                    if (textHandlerIDs != textHandlerID) {

                        MainServer.eb.publish(textHandlerIDs, JSONHandler.convertToJSON(message));
                    }
                }
                room.addMessage(message);
                roomService.updateRoom(room);
            }
            if(gotJSON.getNickName() == null && gotJSON.getMessage() == null) {
                for (String textHandlerIDs : idsRoom) {
                    if (textHandlerIDs != textHandlerID) {

                        MainServer.eb.publish(textHandlerIDs, handler.textData());
                        if (gotJSON.getPlayStatus() != null) {
                            room.playStatusWork.setPlayStatus(gotJSON.getPlayStatus());
                            roomService.updateRoom(room);
                        }
                        if (gotJSON.getTime() != 0) {
                            room.time.setTime(gotJSON.getTime());
                            roomService.updateRoom(room);
                        }
                    }
                }
            }
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
