package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by bikevit2008 on 12.01.16.
 */
public class IniParser {
    /*public static void main(String args[]){
        System.out.println(getLanguage("en"));
    }*/
    public HashMap<String, String> getLanguage(String nameLanguage){
        return parseStrings(parseFile("web/languages/" + nameLanguage + ".ini"));
    }

    public String parseFile(String path){
        String content = null;
        try {
            content = new Scanner(new File(path)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }

    public HashMap<String, String> parseStrings(String file){
        String[] split = file.split("\\n");
        int countStrings = split.length;
        HashMap<String, String> language = new HashMap<String, String>();
        for(int i = 0; i<countStrings; i++){
            String[] splitString = split[i].split("=");
            language.put(splitString[0], splitString[1]);
        }
        return language;
    }
}
