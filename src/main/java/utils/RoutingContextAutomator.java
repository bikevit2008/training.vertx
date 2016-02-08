package utils;

import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.Cookie;
import io.vertx.rxjava.ext.web.RoutingContext;

import java.util.Map;

/**
 * Created by bikevit2008 on 21.01.16.
 */
public class RoutingContextAutomator {
    public static void globalHandle(RoutingContext ctx, Map<String, Object> model, String pathTemplate){
        try {
            ctx.response().putHeader("content-type", "text/html");
            ctx.response().end(JadeEngine.renderTemplate(model, pathTemplate, preferedLanguage(ctx)));
        } catch(IllegalStateException e){
            System.out.println("Response has already been written");
        }
    }
    private static String preferedLanguage(RoutingContext ctx){
        String lang = "";
        try{
            lang = preferedContains(ctx.getCookie("Priority-language").getValue());
        }
        catch (NullPointerException e){
            try{
                lang = preferedContains(ctx.request().getHeader("Accept-Language"));
            }
            catch(NullPointerException error){
                lang = "ru";
            }
        }

        return lang;
    }
    private static String preferedContains(String forCheck){
        String lang = "";
        if(forCheck.contains("ru")){
            lang = "ru";
        }
        else{
            lang = "en";
        }
        return lang;
    }
}
