package controller.forms.post;

import de.neuland.jade4j.Jade4J;
import io.vertx.core.Handler;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.RoutingContext;
import model.entity.Room;
import service.RoomService;
import service.factory.ServiceFactory;
import utils.NamesRoomGenerator;
import utils.RoomNameGenerator;
import utils.URLParser;

import java.io.IOException;
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
        String provider = URLParser.getProvider(linkValueVideo);
        String videoId = URLParser.getVideoID(linkValueVideo);
        System.out.println("Provider: " + provider + " VideoID: " + videoId);
        String errorMessage = "Link is not valid";

        if(provider != errorMessage && videoId != errorMessage){
            String roomUrl = NamesRoomGenerator.GenerateUrl();
        Room room = new Room(roomUrl, provider, videoId);

        roomService.addRoom(room);

        routingContext.response().setStatusCode(301);
        routingContext.response().putHeader("Location", "/room/" + roomUrl);
        routingContext.response().end();
        }
        else{
            HttpServerResponse resp = routingContext.response();
            resp.putHeader("content-type", "text/html");
            Map<String, Object> model = new HashMap<String, Object>();

            // Write to the response and end it
            try {
                String html = Jade4J.render("web/templates/landing.jade", model);

                resp.end(html);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
