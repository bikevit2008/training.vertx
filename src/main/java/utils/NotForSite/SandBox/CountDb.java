package utils.NotForSite.SandBox;

import model.entity.Room;
import model.entity.User;
import model.entity.WSUser;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by bikevit2008 on 27.01.16.
 */
public class CountDb {
    private static final String ROOMS_FILE_NAME = "rooms2";
    private static final String ROOMS_TREE_NAME = "rooms";


    private static DB roomsDb = DBMaker.newFileDB(new File(ROOMS_FILE_NAME)).closeOnJvmShutdown().make();

    private static final BTreeMap<String, Room> rooms = roomsDb.getTreeMap(ROOMS_TREE_NAME);

    public static void main(String args[]){
        System.out.println(rooms.size());
    }
}
