package controller;

import io.vertx.core.Handler;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.core.http.ServerWebSocket;

/**
 * Created by Vitaly on 25.10.15.
 */
public class WebSocketHandler implements Handler<ServerWebSocket> {

    @Override
    public void handle(ServerWebSocket serverWebSocket) {


            serverWebSocket.closeHandler(handler -> {

                System.out.println("Client: " + serverWebSocket.textHandlerID() + " Disconnected.");

            });

            serverWebSocket.toObservable().subscribe(buffer -> {
                System.out.println("Got message " + buffer.toString("UTF-8"));
                //         room.add(socket.textHandlerID());
                System.out.println(serverWebSocket.textHandlerID());
            });
    }
}
