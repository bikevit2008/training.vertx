package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Vitaly on 16.09.15.
 */
public class RoomNameGenerator {
        public static String GenerateUrl(){
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyMMddHHmmssSSSS");
            String date = ft.format(dNow);
            return String.valueOf(replace(date + generate(2, 2)));
        }


        private static StringBuilder replace(String date){
            StringBuilder result = new StringBuilder();
            for (int i = 0; i<18; i++){
                result.append(replaceSymbol(date.substring(i, i + 1)));
            }
            return result;
        }
        private static String replaceSymbol(String dateSymbol){
            String symbols = "abcdefghij";
            int index = Integer.parseInt(dateSymbol);
            String replace = symbols.substring(index, index + 1);
            return replace;
        }

        public static String generate(int from, int to) {
            String pass = "";
            Random r = new Random();
            int cntchars = from + r.nextInt(to - from + 1);

            for (int i = 0; i < cntchars; ++i) {
                char    next = '0';
                int       range = 10;

                pass += (char) ((r.nextInt(range)) + next);

            }
            return pass;
    }
}
