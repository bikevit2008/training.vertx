package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Handler;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.core.http.ServerWebSocket;
import model.entity.User;
import service.UserService;
import service.factory.ServiceFactory;

/**
 * Created by Vitaly on 25.10.15.
 */
public class WebSocketHandler implements Handler<ServerWebSocket> {


    private UserService userService = ServiceFactory.getUserService();

    @Override
    public void handle(ServerWebSocket serverWebSocket) {
        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();
        String RoomUrl = parseRoomUrlWS(serverWebSocket.path());
        // onplay.me/websocket/room/dfsdfsdfsdfsf

        System.out.println("Session: " + serverWebSocket.headers().get("SessionID"));
//        User user = userService.getUserById(serverWebSocket.headers().get("SessionID"));
//        user.setTextHandlerid(serverWebSocket.textHandlerID());
//        userService.updateUser(user);
        //System.out.println(Thread.currentThread().getName());
        //When client disconnect*/
        serverWebSocket.closeHandler(handler -> {
            /*User->1.Session
            *       2.TextHandlerId
            *       3.Nickname */
                //Here we must delete TextHandlerID

                System.out.println("Client: " + serverWebSocket.textHandlerID() + " Disconnected.");

        });
        //Send and get messages from(to) client
        serverWebSocket.toObservable().subscribe(buffer -> {
            //ObjectMapper objectMapper = new ObjectMapper();
           // objectMapper.writeValueAsString();

            System.out.println("RoomUrl: " + RoomUrl);
            System.out.println("Got message " + buffer.toString("UTF-8"));
            //         room.add(socket.textHandlerID());
            System.out.println(serverWebSocket.textHandlerID());

        });
    }
    private String parseRoomUrlWS(String path){
        return path.substring(16);
    }
}
