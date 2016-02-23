package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by bikevit2008 on 12.01.16.
 */
public class LangParser {
    private static final String pathToLanguages = "web/languages/";

    /*public static void main(String args[]){
        System.out.println(getLanguages());
    }*/

    public static HashMap<String, Object> getLanguage(String nameLanguage){
        return parseStrings(parseFile(pathToLanguages + nameLanguage + ".ini"));
    }

    public static String parseFile(String path){
        String content = "";
        Scanner file = null;
        try {
            file = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(file.hasNextLine()) {
                content = file.useDelimiter("\\Z").next();
        }
        return content;
    }

    public static HashMap<String, Object> parseStrings(String file){
        String[] split = file.split("\\n");
        int countStrings = split.length;
        HashMap<String, Object> language = new HashMap<String, Object>();
        if(file.length() != 0) {
            for (int i = 0; i < countStrings; i++) {
                String[] splitString = split[i].split(":=");
                language.put(splitString[0], splitString[1]);
            }
        }
        return language;
    }
    public static HashMap<String, HashMap<String, Object>> getLanguages(){
        HashMap<String, HashMap<String, Object>> languages = new HashMap<>();
        File file = new File(pathToLanguages);
        File[] s = file.listFiles();
        for (int j = 0; j < s.length; j++) {
            if(!s[j].isDirectory()) {
                String languageNameBeforeDot =  s[j].getName().split("\\.")[0];
                languages.put(languageNameBeforeDot, getLanguage(languageNameBeforeDot));
            }
        }
        return languages;
    }
}
