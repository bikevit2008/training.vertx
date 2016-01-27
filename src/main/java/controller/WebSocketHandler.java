package controller;

import io.vertx.core.Handler;
import io.vertx.rxjava.core.http.ServerWebSocket;
import model.entity.*;
import server.MainServer;
import service.IdsService;
import service.RoomService;
import service.UserService;
import service.factory.ServiceFactory;
import utils.JSONParser;

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
        ArrayList<WSUser> idsRoom = idsService.getRoomByUrl(roomUrl);
        String textHandlerID = serverWebSocket.textHandlerID();
        Long[] ping = {null};
        Long[] sentTime = {null};
        // onplay.me/websocket/room/dfsdfsdfsdfsf

        String sessionId = parseCookieWS(serverWebSocket.headers().get("Cookie"));
        System.out.println("Session: " + sessionId);
        User user = userService.getUserById(sessionId);
        userService.updateUser(user);
        WSUser wsUser = new WSUser(textHandlerID, ping[0]);
        idsRoom.add(wsUser);
        idsService.updateRoom(roomUrl, idsRoom);

        room.countUsers.setCountUsers(idsRoom.size());
        roomService.updateRoom(room);
        System.out.println(room.countUsers.getCountUsers());

        //Send new countUsers to all
        for (WSUser textHandlerIDs : idsRoom) {
//            MainServer.eb.publish(textHandlerIDs.getTextHandlerId(), JSONParser.convertToJSON(room.countUsers));
        }
        //When client connected
//        MainServer.eb.publish(textHandlerID, JSONParser.convertToJSON(room.playStatusWork));
//        MainServer.eb.publish(textHandlerID, JSONParser.convertToJSON(room.time));
        //Messages inserted to html

        Long timer = MainServer.vertx.setPeriodic(1000,  (handler->{
            sentTime[0] = System.nanoTime();
            System.out.println("Sent time: " + sentTime[0]);
            MainServer.eb.publish(textHandlerID, JSONParser.convertToJSON(MainServer.ping));
        }));

        //When client disconnect*/
        serverWebSocket.closeHandler(handler -> {

            MainServer.vertx.cancelTimer(timer);

            idsRoom.remove(wsUser);
            idsService.updateRoom(roomUrl, idsRoom);

            room.countUsers.setCountUsers(idsRoom.size());
            roomService.updateRoom(room);

            //Send new countUsers to all
            for (WSUser textHandlerIDs : idsRoom){
                MainServer.eb.publish(textHandlerIDs.getTextHandlerId(), JSONParser.convertToJSON(room.countUsers));
            }
            System.out.println(room.countUsers.getCountUsers());
            System.out.println("Client: " + serverWebSocket.textHandlerID() + " Disconnected.");

        });
        serverWebSocket.frameHandler(handler -> {
            System.out.println("Got message: " + handler.textData());
            GotJSON gotJSON = JSONParser.convertFromJSON(handler.textData());


            if (gotJSON.getUtils() != null){
                ping[0] = (System.nanoTime() - sentTime[0])/2 /1000000;
                wsUser.setPing(ping[0]);
                System.out.println("Ping: " + wsUser.getPing());
                idsService.updateRoom(roomUrl, idsRoom);

            }

            if(gotJSON.getNickName() != null){
                user.setNickName(gotJSON.getNickName());
                userService.updateUser(user);
            }

            if (gotJSON.getMessage() != null) {
                Message message = new Message(gotJSON.getMessage(), user.getNickName());
                for (WSUser textHandlerIDs : idsRoom) {
                    if (textHandlerIDs.getTextHandlerId() != textHandlerID) {

                        MainServer.eb.publish(textHandlerIDs.getTextHandlerId(), JSONParser.convertToJSON(message));
                    }
                }
                room.addMessage(message);
                roomService.updateRoom(room);
            }

                        if (gotJSON.getPlayStatus() != null) {
                            for (WSUser textHandlerIDs : idsRoom) {
                                if (textHandlerIDs.getTextHandlerId() != textHandlerID) {
                                    MainServer.eb.publish(textHandlerIDs.getTextHandlerId(), handler.textData());
                                }
                            }
                            room.playStatusWork.setPlayStatus(gotJSON.getPlayStatus());
                            roomService.updateRoom(room);
                        }
                        if (gotJSON.getTime() != 0) {
                            double timeWithPingSender = gotJSON.getTime() + ping[0];
                            room.time.setTime(timeWithPingSender);
                            roomService.updateRoom(room);

                            for (WSUser textHandlerIDs : idsRoom) {
                                if (textHandlerIDs.getTextHandlerId() != textHandlerID) {
                                    MainServer.eb.publish(textHandlerIDs.getTextHandlerId(), JSONParser.convertToJSON(new Time(room.time.getTime() + textHandlerIDs.getPing())));
                                    System.out.println(room.time.getTime() + textHandlerIDs.getPing());
                                }
                            }
                            room.time.setTime(gotJSON.getTime());
                            roomService.updateRoom(room);
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
