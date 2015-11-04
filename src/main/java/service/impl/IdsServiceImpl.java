package service.impl;

import model.dao.IdsDao;
import model.dao.factory.DaoFactory;
import model.entity.WSUser;
import service.IdsService;

import java.util.ArrayList;

/**
 * Created by Vitaly on 28.10.15.
 */
public class IdsServiceImpl implements IdsService {

    private IdsDao idsDao = DaoFactory.getIdsDao();


    public ArrayList<WSUser> addRoom(String roomUrl, ArrayList<WSUser> arrayList) {
        return idsDao.insertRoom(roomUrl, arrayList);
    }

    public ArrayList<WSUser> updateRoom(String roomUrl, ArrayList<WSUser> arrayList) {
        return idsDao.updateRoom(roomUrl, arrayList);
    }

    public ArrayList<WSUser> getRoomByUrl(String roomUrl) {
        return idsDao.findRoomById(roomUrl);
    }

}
