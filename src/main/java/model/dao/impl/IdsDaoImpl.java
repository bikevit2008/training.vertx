package model.dao.impl;

import model.dao.factory.DbService;
import model.entity.WSUser;
import org.mapdb.BTreeMap;

import java.util.ArrayList;

/**
 * Created by Vitaly on 28.10.15.
 */
public class IdsDaoImpl implements model.dao.IdsDao {

    private BTreeMap<String, ArrayList<WSUser>> ids = DbService.getIdsTreeMap();

    @Override
    public ArrayList<WSUser> insertRoom(String roomUrl, ArrayList<WSUser> arrayList) {
        ArrayList<WSUser> result = ids.putIfAbsent(roomUrl, arrayList);
        return result;    }

    @Override
    public ArrayList<WSUser> updateRoom(String roomUrl, ArrayList<WSUser> arrayList) {
        ArrayList<WSUser> result = ids.replace(roomUrl, arrayList);
        return result;
    }

    @Override
    public ArrayList<WSUser> findRoomById(String roomUrl) {
        return ids.get(roomUrl);
    }

}
