package database;

import java.util.HashMap;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 * Created by Vitaly on 21.09.15.
 */
public class ConcurrentNavigableMaps {
    //This Map will contain Rooms, which contain all information and links
    //Example content-type (music, video, etc), content-provider (youtube, vimeo, soundcloud, etc)
    public static ConcurrentNavigableMap<String,HashMap> data = databaseUse.getTreeMap("data");

    //This method create new Room in Map
    public static void addNew(String name){
        data.put(name, new HashMap<String, String>());
    };
    //This method remove Room from Map
    public static void remove(String name){
        data.remove(name);
    };
    //This method give all information from current Room
    public static HashMap get(String name){
        return
                data.get(name);
    };
    //This method put Room with parameters
    public static void put(String name, HashMap hash){
        data.put(name, hash);
    };
    //This method
    public static int size(){
        return
                data.size();
    };
    //This method remove all information from Map
    private static void clear(){
        data.clear();
    };
    //This method checks existence of the Room in Map
    public static boolean containsKey(String key){
        return
                data.containsKey(key);
    };
    //This method checks existence of the value in Map, i think it's useless:)
    public static boolean containsValue(HashMap hash){
        return
                data.containsValue(hash);
    };
}
