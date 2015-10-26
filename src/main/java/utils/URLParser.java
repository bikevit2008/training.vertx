package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Игорь on 09.10.2015.
 */
public class URLParser {


    static String errorMessage = "Link is not valid";
    static String url = "25451551";
    public static String provider;


    public static void main(String[] args) {

        System.out.println(getProvider(url)+"   "+getVideoID(url));

    }

    public static String getVideoID(String url) {
        if(provider != null){
        String pattern = "";

        switch (provider) {
            case "youtube":

                pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

                break;
            case "vimeo":

                pattern = "(\\d+)";

                break;
            default:

                return errorMessage;  //Letter we can use throw new


        }
        Pattern compiledPattern = Pattern.compile(pattern);

        Matcher matcher = compiledPattern.matcher(url); //url is youtube or vimeo url for which you want to extract the id.

        if (matcher.find()) {

            return matcher.group();

        } else {

            return errorMessage;  //Letter we can use throw new
        }
    }
        else {return errorMessage;}
    }


    public static String getProvider(String url) {

        if (url.contains("youtu")) {

            provider = "youtube";

        } else if (url.contains("vimeo")) {

            provider = "vimeo";

        } else {

            return errorMessage;

        }

        return provider;

    }
}