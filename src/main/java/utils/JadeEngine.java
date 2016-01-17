package utils;

import de.neuland.jade4j.Jade4J;

import java.io.IOException;
import java.util.Map;

/**
 * Created by bikevit2008 on 13.01.16.
 */
public class JadeEngine {
    public static String renderTemplate(Map model, String pagePath){
    String html = null;
    // Write to the response and end it
    try {
        html = Jade4J.render("web/jade/" + pagePath + ".jade", model);

    } catch (IOException e) {
        e.printStackTrace();
    }
    return html;
}
}
