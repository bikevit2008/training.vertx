package database;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;


/**
 * Created by Vitaly on 16.09.15.
 */
public class databaseUse {
    private static DB db = DBMaker
            .newFileDB(new File("DB"))
            .closeOnJvmShutdown()
            .make();

    public static Map<String, Object> getAll(DB name){
        return
                name.getAll();
    }
    public static void commit(){
        db.commit();
    }
    public static void getHashMap(String name){
        db.getHashMap(name);
    }

    public static ConcurrentNavigableMap<String, HashMap> getTreeMap(String name){
        return
                db.getTreeMap(name);
    }

    public static void getHashSet(String name){
        db.getHashSet(name);
    }
    public static void getTreeSet(String name){
        db.getTreeSet(name);
    };
    public static boolean exists(String name){
        return
                db.exists(name);
    };
    public static void delete(String name){
        db.delete(name);
    };
    public static void rename(String oldName, String newName){
        db.rename(oldName, newName);
    };
    public static void rollback(){
        db.rollback();
    };

    private static DB clients = DBMaker
            .newFileDB(new File("Clients"))
            .closeOnJvmShutdown()
            .deleteFilesAfterClose()
            .make();

}
