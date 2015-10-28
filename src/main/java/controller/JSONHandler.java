package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.entity.CountUsers;

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
    public static CountUsers convertFromJSON(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        CountUsers countUsers = null;
        try {
            countUsers = objectMapper.readValue(json, CountUsers.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countUsers;
    }

}
