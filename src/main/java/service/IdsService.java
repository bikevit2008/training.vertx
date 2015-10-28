package service;

import java.util.ArrayList;

/**
 * Created by Vitaly on 28.10.15.
 */
public interface IdsService {

    ArrayList<String> addRoom(String roomUrl, ArrayList<String> arrayList);

    ArrayList<String> updateRoom(String roomUrl, ArrayList<String> arrayList);

    ArrayList<String> getRoomByUrl(String roomUrl);
}
