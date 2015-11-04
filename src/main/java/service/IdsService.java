package service;

import model.entity.WSUser;

import java.util.ArrayList;

/**
 * Created by Vitaly on 28.10.15.
 */
public interface IdsService {

    ArrayList<WSUser> addRoom(String roomUrl, ArrayList<WSUser> arrayList);

    ArrayList<WSUser> updateRoom(String roomUrl, ArrayList<WSUser> arrayList);

    ArrayList<WSUser> getRoomByUrl(String roomUrl);
}
