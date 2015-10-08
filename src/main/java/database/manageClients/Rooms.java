package database.manageClients;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 * Created by Vitaly on 14.09.15.
 */
public class Rooms {

    private static DB clients = DBMaker
            .newFileDB(new File("Clients"))
            .closeOnJvmShutdown()
            .deleteFilesAfterClose()
            .make();

    public static ConcurrentNavigableMap<String, LinkedHashSet<String>> all = clients.getTreeMap("clients");

    public static void addNew(String name){
        all.put(name, new LinkedHashSet<String>());
    };
    public static void remove(String name){
        all.remove(name);
    };
    public static LinkedHashSet<String> get(String name){
        return
        all.get(name);
    };
    public static void put(String name, LinkedHashSet hash){
        all.put(name, hash);
    };
    public static int size(){
        return
                all.size();
    };
    public static void clear(){
        all.clear();
    };
    public static boolean containsKey(String key){
        return
                all.containsKey(key);
    };
    public static boolean containsValue(LinkedHashSet<String> hash){
        return
                all.containsValue(hash);
    };
}
