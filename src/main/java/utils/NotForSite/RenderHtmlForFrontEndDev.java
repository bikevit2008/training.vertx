package utils.NotForSite;

import model.entity.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static utils.JadeEngine.renderTemplate;

/**
 * Created by bikevit2008 on 08.02.16.
 */
public class RenderHtmlForFrontEndDev {
    public static void main(String args[]){
        System.out.println(renderRoom());
    }
    private static String renderHome(){
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("validationLink", "validLink");
        model.put("displayView", "displayNone");
        String html = renderTemplate(model, "home/landing", "ru");
        return html;
    }
    private static String renderRoom(){

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("videoId", "3AutQaSlEuk");
        model.put("messages", new ArrayList<Message>());
        String html = renderTemplate(model, "room/room", "ru");
        return html;
    }
}
