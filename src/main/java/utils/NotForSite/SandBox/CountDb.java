package utils.NotForSite.SandBox;

import model.entity.Room;
import model.entity.User;
import model.entity.WSUser;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by bikevit2008 on 27.01.16.
 */
public class CountDb {
    private static final String ROOMS_FILE_NAME = "rooms";
    private static final String ROOMS_TREE_NAME = "rooms";


    private static DB roomsDb = DBMaker.newFileDB(new File(ROOMS_FILE_NAME)).closeOnJvmShutdown().make();

    private static final BTreeMap<String, Room> rooms = roomsDb.getTreeMap(ROOMS_TREE_NAME);


    private static final String deReplaceSymbol(String urlSymbol){
        String symbols = "abcdefghij";
        int index = symbols.indexOf(urlSymbol);
        String dateSymbol = String.valueOf(index);
        return dateSymbol;
    }
    private static final StringBuilder deReplace(String url){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < 12; i++){
            result.append(deReplaceSymbol(url.substring(i, i+1)));
            if(i % 2 == 1){
                if(i<5) {
                    result.append(".");
                }
                if(i == 5){
                    result.append(" ");
                }
                if(i >= 6 && i < 11){
                    result.append(":");
                }
            }
        }
        return result;
    }
    private static final long getMessagesSize(String messages[]){
        return messages.length;
    }
    private static final void countAllMessages(){
        long messagesSize = 0;
        for(BTreeMap.Entry<String, Room> entry : rooms.entrySet()){
            messagesSize += entry.getValue().messages.size();
        }
        System.out.println(messagesSize);
    }
    private static final void countRoomsWithMessages(){
        long countRooms = 0;
        for(BTreeMap.Entry<String, Room> entry : rooms.entrySet()){
            if(!entry.getValue().messages.isEmpty()) {
                countRooms++;
            }
        }
        System.out.println(countRooms);
    }
    private static final void getVideoLink(Room room){
        String result = "";
        if(room.getProvider().equals("youtube")) {
            System.out.println(room.getVideoId());
            result = "https://www.youtube.com/watch?v=" + room.getVideoId();
            System.out.println(result);
        }
        else{
            System.out.println("Unknown provider");
        }

    }
    public static void main(String args[]){
        getVideoLink(rooms.firstEntry().getValue());
        System.out.println(rooms.firstEntry().getValue().getProvider());
    }

}
