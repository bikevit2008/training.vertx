package model.dao;

import model.entity.WSUser;

import java.util.ArrayList;

/**
 * Created by Vitaly on 28.10.15.
 */
public interface IdsDao {

    ArrayList<WSUser> insertRoom(String roomUrl, ArrayList<WSUser> arrayList);

    ArrayList<WSUser> updateRoom(String roomUrl, ArrayList<WSUser> arrayList);

    ArrayList<WSUser> findRoomById(String roomUrl);
}
