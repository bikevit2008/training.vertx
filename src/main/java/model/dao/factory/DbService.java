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
public class DbService {

    private static final String USERS_FILE_NAME = "transient";
    private static final String USERS_TREE_NAME = "users";

    private static final String ROOMS_FILE_NAME = "permanent";
    private static final String ROOMS_TREE_NAME = "rooms";


    private static DB usersDb = DBMaker.newFileDB(new File(USERS_FILE_NAME)).closeOnJvmShutdown().make();
    //TODO Don't delete on shutdown in production
    private static DB roomsDb = DBMaker.newFileDB(new File(ROOMS_FILE_NAME)).closeOnJvmShutdown().make();

    private static final BTreeMap<String, User> users = usersDb.createTreeMap(USERS_TREE_NAME).make();
    private static final BTreeMap<String, Room> rooms = roomsDb.createTreeMap(ROOMS_TREE_NAME).make();


    public static BTreeMap<String, User> getUsersTreeMap() {
        return users;
    }

    public static BTreeMap<String, Room> getRoomsTreeMap() {
        return rooms;
    }

    public static void commitUser() {
        usersDb.commit();
    }

    public static void commitRoom() {
        roomsDb.commit();
    }

}
