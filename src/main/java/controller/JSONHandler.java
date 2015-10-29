package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.entity.CountUsers;
import model.entity.GotJSON;

import java.io.IOException;

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
