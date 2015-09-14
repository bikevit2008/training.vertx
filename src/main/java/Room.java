/**
 * Created by Vitaly on 14.09.15.
 */
public class Room {
    public static void addClient(String name, String textHandlerID){
        Rooms.get(name).add(textHandlerID);
    };
    public static void removeClient(String name, String textHandlerID){
        Rooms.get(name).remove(textHandlerID);
    };
    public static void clear(String name){
        Rooms.get(name).clear();
    };
    public static boolean contains(String name, String textHandlerID){
        return
                Rooms.get(name).contains(textHandlerID);
    };
    public static boolean isEmpty(String name){
        return
                Rooms.get(name).isEmpty();
    };
    public static int size(String name){
        return
               Rooms.get(name).size();
    };
}
