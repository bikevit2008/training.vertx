package model.dao.factory;

import model.dao.MessageDao;
import model.dao.RoomDao;
import model.dao.UserDao;
import model.dao.impl.MessageDaoImpl;
import model.dao.impl.RoomDaoImpl;
import model.dao.impl.UserDaoImpl;

/**
 * Created by denis on 13/10/15.
 */
public class DaoFactory {

    public static RoomDao getRoomDao() {
        return new RoomDaoImpl();
    }

    public static MessageDao getMessageDao() {
        return new MessageDaoImpl();
    }

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

}
