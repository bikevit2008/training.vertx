package service.impl;

import model.dao.RoomDao;
import model.dao.factory.DaoFactory;
import model.entity.Message;
import model.entity.Room;
import model.entity.User;
import service.RoomService;

import java.util.List;

/**
 * Created by denis on 13/10/15.
 */
public class RoomServiceImpl implements RoomService{

    RoomDao roomDao = DaoFactory.getRoomDao();

    public Room addRoom(int id) {
        return addRoom(id, null, null);
    }

    public Room addRoom(int id, List<User> users) {
        return addRoom(id, users, null);
    }

    public Room addRoom(int id, List<User> users, List<Message> messages) {
        return roomDao.insertRoom(new Room(id, users, messages));
    }

    public Room removeRoom(Room room) {
        return roomDao.removeRoom(room);
    }

    public Room removeRoomById(int id) {
        return roomDao.removeRoomById(id);
    }

    public Room updateRoom(Room room) {
        return roomDao.updateRoom(room);
    }

    public Room getRoomById(int id) {
        return roomDao.findRoomById(id);
    }

}
