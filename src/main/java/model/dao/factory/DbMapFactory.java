package model.dao.factory;

import model.entity.Room;
import model.entity.User;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;

/**
 * Created by denis on 21/10/15.
 */
public class DbMapFactory {

    private static final String TRANSIENT_FILE_NAME = "transient";
    private static final String USERS_TREE_NAME = "users";

    private static final String PERMANENT_FILE_NAME = "permanent";
    private static final String ROOMS_TREE_NAME = "rooms";

    private static DB transientDb = DBMaker.newFileDB(new File(TRANSIENT_FILE_NAME)).closeOnJvmShutdown().make();
    //TODO Don't delete on shutdown in production
    private static DB permanentDb = DBMaker.newFileDB(new File(PERMANENT_FILE_NAME)).closeOnJvmShutdown().make();

    private static final BTreeMap<String, User> users = transientDb.createTreeMap(USERS_TREE_NAME).make();
    private static final BTreeMap<String, Room> rooms = permanentDb.createTreeMap(ROOMS_TREE_NAME).make();

    public static BTreeMap<String, User> getUsersTreeMap() {
        return users;
    }

    public static BTreeMap<String, Room> getRoomsTreeMap() {
        return rooms;
    }

}
