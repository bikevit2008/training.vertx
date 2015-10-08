package database.manageClients;

/**
 * Created by Vitaly on 14.09.15.
 */
public class Room {
    public static void addClient(String nameRoom, String textHandlerID){
        Rooms.get(nameRoom).add(textHandlerID);
    };
    public static void removeClient(String nameRoom, String textHandlerID){
        Rooms.get(nameRoom).remove(textHandlerID);
    };
    public static void clear(String nameRoom){
        Rooms.get(nameRoom).clear();
    };
    public static boolean contains(String nameRoom, String textHandlerID){
        return
                Rooms.get(nameRoom).contains(textHandlerID);
    };
    public static boolean isEmpty(String nameRoom){
        return
                Rooms.get(nameRoom).isEmpty();
    };
    public static int size(String nameRoom){
        return
               Rooms.get(nameRoom).size();
    };
}
