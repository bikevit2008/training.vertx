package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by Vitaly on 27.10.15.
 */
public class JSONHandler {
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
   /* public static Object convertFromJSON(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        Object object = objectMapper.readValue(json);
        return object;
    }*/

}
