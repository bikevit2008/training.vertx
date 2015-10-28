package service.impl;

import model.dao.IdsDao;
import model.dao.factory.DaoFactory;
import service.IdsService;

import java.util.ArrayList;

/**
 * Created by Vitaly on 28.10.15.
 */
public class IdsServiceImpl implements IdsService {

    private IdsDao idsDao = DaoFactory.getIdsDao();


    public ArrayList<String> addRoom(String roomUrl, ArrayList<String> arrayList) {
        return idsDao.insertRoom(roomUrl, arrayList);
    }

    public ArrayList<String> updateRoom(String roomUrl, ArrayList<String> arrayList) {
        return idsDao.updateRoom(roomUrl, arrayList);
    }

    public ArrayList<String> getRoomByUrl(String roomUrl) {
        return idsDao.findRoomById(roomUrl);
    }

}
