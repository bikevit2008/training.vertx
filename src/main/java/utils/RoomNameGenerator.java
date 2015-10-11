package utils;

import java.util.Random;


/**
 * Created by Игорь on 11.10.2015.
 */

public class RoomNameGenerator {

    public static String generate(int from, int to) {
        String pass = "";
        Random r = new Random();
        int cntchars = from + r.nextInt(to - from + 1);

        for (int i = 0; i < cntchars; ++i) {
            char next = 0;
            int range = 10;


            switch (r.nextInt(3)) {
                case 0: {
                    next = '0';
                    range = 10;
                }
                break;
                case 1: {
                    next = 'a';
                    range = 26;
                }
                break;
                case 2: {
                    next = 'A';
                    range = 26;
                }
                break;
            }


            pass += (char) ((r.nextInt(range)) + next);

        }


        return pass;
    }

    public static void main(String args[]) {
        String pass = generate(8, 12);
        System.out.println(pass);
    }

}
