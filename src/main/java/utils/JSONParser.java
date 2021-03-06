package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import model.entity.GotJSON;
import model.entity.Time;

import java.io.IOException;

/**
 * Created by Vitaly on 27.10.15.
 */
public class JSONParser {
    public static void main(String args[]){
//        Time time = new Time();
//        time.setTime(123);
//        //System.out.println(convertToJSON(time));
//        String json = "{\"playStatus\":\"PAUSE\"}";
//        JsonObject object = new JsonObject(json);
//        System.out.println("Json: "+object);
//
//        System.out.println(object.fieldNames());
    }
    public static String convertToJSON(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonReturn = "";
        try {
            jsonReturn = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonReturn;
    }
    public static GotJSON convertFromJSON(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        GotJSON gotJSON = null;
        try {
            gotJSON = objectMapper.readValue(json, GotJSON.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gotJSON;
    }

}
