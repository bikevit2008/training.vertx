package controller.forms.post;

import io.vertx.core.Handler;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.RoutingContext;
import model.entity.PlayStatus;
import model.entity.Room;
import service.RoomService;
import service.factory.ServiceFactory;
import utils.JadeEngine;
import utils.RoomNameGenerator;
import utils.RoutingContextAutomator;
import utils.URLParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vitaly on 21.10.15.
 */
public class CreateRoomHandler implements Handler<RoutingContext> {

    private RoomService roomService = ServiceFactory.getRoomService();

    @Override
    public void handle(RoutingContext routingContext) {

        String linkValueVideo = routingContext.request().getFormAttribute("linkValue");
        System.out.println(linkValueVideo);
        String provider = URLParser.getProvider(linkValueVideo);
        String videoId = URLParser.getVideoID(linkValueVideo, provider);
        System.out.println("Provider: " + provider + " VideoID: " + videoId);
        String errorMessage = "Link is not valid";
        HttpServerResponse response = routingContext.response();
        if(provider != errorMessage && videoId != errorMessage){
            String roomUrl = RoomNameGenerator.GenerateUrl();
            Room room = new Room(roomUrl, provider, videoId, PlayStatus.PAUSE, 0L, 0);

            roomService.addRoom(room);

            response.setStatusCode(301);
            response.putHeader("Location", "/room/" + roomUrl);
            response.end();
        }
        else{
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("validationLink", "validLink");
            model.put("displayView", "displayBlock");
            model.put("error", "error");
            model.put("valueOfIncorrectLink", linkValueVideo);

            RoutingContextAutomator.globalHandle(routingContext, model, "home/landing", response);

        }
    }
}
