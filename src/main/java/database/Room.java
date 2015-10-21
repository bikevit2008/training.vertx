package database;

/**
 * Created by Vitaly on 16.09.15.
 */
public class Room {
    public static void put(String nameRoom, String key, String value) {
        ConcurrentNavigableMaps.get(nameRoom).put(key, value);
    }

    ;

    public static void remove(String nameRoom, String key) {
        ConcurrentNavigableMaps.get(nameRoom).remove(key);
    }

    ;

    public static Object get(String nameRoom, String key) {
        return
                ConcurrentNavigableMaps.get(nameRoom).get(key);
    }

    ;

    public static boolean isEmpty(String nameRoom) {
        return
                ConcurrentNavigableMaps.get(nameRoom).isEmpty();
    }

    ;

    public static int size(String nameRoom) {
        return
                ConcurrentNavigableMaps.get(nameRoom).size();
    }

    ;

    private static void clear(String nameRoom) {
        ConcurrentNavigableMaps.get(nameRoom).clear();
    }

    ;

    public static boolean containsKey(String nameRoom, String key) {
        return
                ConcurrentNavigableMaps.get(nameRoom).containsKey(key);
    }

    ;

    public static boolean containsValue(String nameRoom, String value) {
        return
                ConcurrentNavigableMaps.get(nameRoom).containsKey(value);
    }

    ;
}
