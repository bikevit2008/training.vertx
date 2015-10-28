package utils;

/**
 * Created by Vitaly on 28.10.15.
 */
public class UrlGenerator {
    static String errorMessage = "I can't generate link";
    public static String generateUrl(String provider, String videoId) {
        String linkResult = "";
        switch (provider) {
            case "youtube":

                linkResult = "https://www.youtube.com/embed/" + videoId +"?controls=0&rel=0&disablekb=1&fs=0&showinfo=0&enablejsapi=1";
                break;
            case "vimeo":


            break;
            default:

                return errorMessage;  //Letter we can use throw new
        }
        return linkResult;
    }
}
