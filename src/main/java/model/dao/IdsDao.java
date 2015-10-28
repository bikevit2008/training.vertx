package model.dao;

import java.util.ArrayList;

/**
 * Created by Vitaly on 28.10.15.
 */
public interface IdsDao {

    ArrayList<String> insertRoom(String roomUrl, ArrayList<String> arrayList);

    ArrayList<String> updateRoom(String roomUrl, ArrayList<String> arrayList);

    ArrayList<String> findRoomById(String roomUrl);
}
