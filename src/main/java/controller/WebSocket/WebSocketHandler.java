package controller.WebSocket;

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

        String sessionId = parseCookieWS(serverWebSocket.headers().get("Cookie"));
        User user = userService.getUserById(sessionId);
        userService.updateUser(user);
        WSUser wsUser = new WSUser(textHandlerID);
        idsRoom.add(wsUser);
        idsService.updateRoom(roomUrl, idsRoom);

        room.countUsers.setCountUsers(idsRoom.size());
        roomService.updateRoom(room);

        //When client connected
        //Send new countUsers to all
        for (WSUser textHandlerIDs : idsRoom) {
            MainServer.eb.publish(textHandlerIDs.getTextHandlerId(), JSONParser.convertToJSON(room.countUsers));
        }

        if(room.firstPlayTime != 0L){
            System.out.println("When client has been connected: " + room.firstPlayTime);
            room.time.setTime(room.time.getTime() + (System.currentTimeMillis() - room.firstPlayTime) / 1000);
        }
        if(room.time.getTime() != 0){
            MainServer.eb.publish(textHandlerID, JSONParser.convertToJSON(room.playStatusWork));
            MainServer.eb.publish(textHandlerID, JSONParser.convertToJSON(room.time));
        }
        //Messages inserted to html before websocket was opened

        //When client disconnect*/
        serverWebSocket.closeHandler(handler -> {
            //
//            MainServer.vertx.cancelTimer(timer);
            idsRoom.remove(wsUser);
            idsService.updateRoom(roomUrl, idsRoom);

            room.countUsers.setCountUsers(idsRoom.size());
            roomService.updateRoom(room);

            //Send new countUsers to all
            for (WSUser textHandlerIDs : idsRoom){
                MainServer.eb.publish(textHandlerIDs.getTextHandlerId(), JSONParser.convertToJSON(room.countUsers));
            }

            if(room.countUsers.getCountUsers() == 0){
                if(room.firstPlayTime != 0L){
                    room.time.setTime(room.time.getTime() + (System.currentTimeMillis() - room.firstPlayTime) / 1000);
                    room.firstPlayTime = 0L;
                    room.playStatusWork.setPlayStatus(PlayStatus.PAUSE);
                }
            }
        });


        serverWebSocket.frameHandler(handler -> {
            GotJSON gotJSON = JSONParser.convertFromJSON(handler.textData());


            /*if (gotJSON.getUtils() != null){
                System.out.println("Ping: " + wsUser.getPing());
                idsService.updateRoom(roomUrl, idsRoom);

            }*/

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
                if(gotJSON.getPlayStatus() != room.playStatusWork.getPlayStatus()) {
                    if(gotJSON.getPlayStatus() == PlayStatus.PLAY){
                        room.firstPlayTime = System.currentTimeMillis();
                    }
                    if(gotJSON.getPlayStatus() == PlayStatus.PAUSE){
                        room.time.setTime(room.time.getTime() + (System.currentTimeMillis() - room.firstPlayTime) / 1000);
                        room.firstPlayTime = 0L;
                    }
                    for (WSUser textHandlerIDs : idsRoom) {
                        if (textHandlerIDs.getTextHandlerId() != textHandlerID) {
                            MainServer.eb.publish(textHandlerIDs.getTextHandlerId(), handler.textData());
                        }
                    }
                    room.playStatusWork.setPlayStatus(gotJSON.getPlayStatus());
                    roomService.updateRoom(room);
                }
            }

            if (gotJSON.getTime() != 0) {
                for (WSUser textHandlerIDs : idsRoom) {
                    if (textHandlerIDs.getTextHandlerId() != textHandlerID) {
                        MainServer.eb.publish(textHandlerIDs.getTextHandlerId(), handler.textData());
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
