package model.dao.factory;

import model.entity.Room;
import model.entity.User;
import model.entity.WSUser;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by denis on 21/10/15.
 */
public class DbService {

    private static final String USERS_FILE_NAME = "users";
    private static final String USERS_TREE_NAME = "users";

    private static final String ROOMS_FILE_NAME = "rooms";
    private static final String ROOMS_TREE_NAME = "rooms";

    private static final String TEXTHANDLERIDS_FILE_NAME = "textHandlerIDS";
    private static final String TEXTHANDLERIDS_TREE_NAME = "ids";

    private static DB textHandlerIDS = DBMaker.newFileDB(new File(TEXTHANDLERIDS_FILE_NAME)).closeOnJvmShutdown().deleteFilesAfterClose().make();
    private static DB usersDb = DBMaker.newFileDB(new File(USERS_FILE_NAME)).closeOnJvmShutdown().make();
    private static DB roomsDb = DBMaker.newFileDB(new File(ROOMS_FILE_NAME)).closeOnJvmShutdown().make();

    private static final BTreeMap<String, User> users = usersDb.getTreeMap(USERS_TREE_NAME);
    private static final BTreeMap<String, Room> rooms = roomsDb.getTreeMap(ROOMS_TREE_NAME);
    private static final BTreeMap<String, ArrayList<WSUser>> ids = textHandlerIDS.getTreeMap(TEXTHANDLERIDS_TREE_NAME);



    public static BTreeMap<String, User> getUsersTreeMap() {
        return users;
    }

    public static BTreeMap<String, Room> getRoomsTreeMap() {
        return rooms;
    }

    public static BTreeMap<String, ArrayList<WSUser>> getIdsTreeMap() {
        return ids;
    }



    public static void commitUser() {
    usersDb.commit();
    }

    public static void commitRoom() {
        roomsDb.commit();
    }

}
