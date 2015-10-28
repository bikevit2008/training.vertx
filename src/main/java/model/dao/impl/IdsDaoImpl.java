package model.dao.impl;

import model.dao.factory.DbService;
import org.mapdb.BTreeMap;

import java.util.ArrayList;

/**
 * Created by Vitaly on 28.10.15.
 */
public class IdsDaoImpl implements model.dao.IdsDao {

    private BTreeMap<String, ArrayList<String>> ids = DbService.getIdsTreeMap();

    @Override
    public ArrayList<String> insertRoom(String roomUrl, ArrayList<String> arrayList) {
        ArrayList<String> result = ids.putIfAbsent(roomUrl, arrayList);
        return result;    }

    @Override
    public ArrayList<String> updateRoom(String roomUrl, ArrayList<String> arrayList) {
        ArrayList<String> result = ids.replace(roomUrl, arrayList);
        return result;
    }

    @Override
    public ArrayList<String> findRoomById(String roomUrl) {
        return ids.get(roomUrl);
    }

}
