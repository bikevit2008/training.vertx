package service.impl;

import model.dao.RoomDao;
import model.dao.factory.DaoFactory;
import model.entity.Message;
import model.entity.PlayStatus;
import model.entity.Room;
import model.entity.User;
import service.RoomService;

import java.util.List;

/**
 * Created by denis on 13/10/15.
 */
public class RoomServiceImpl implements RoomService {

    private RoomDao roomDao = DaoFactory.getRoomDao();

    public Room addRoom(Room room) {
        return roomDao.insertRoom(room);
    }

    public Room addRoom(String roomUrl, String provider, String videoId, PlayStatus playStatus, Long time, int countUsers) {
        return roomDao.insertRoom(new Room(roomUrl, provider, videoId, playStatus, time, countUsers));
    }

    public Room removeRoom(Room room) {
        return roomDao.removeRoom(room);
    }

    public Room removeRoomById(String id) {
        return roomDao.removeRoomById(id);
    }

    public Room updateRoom(Room room) {
        return roomDao.updateRoom(room);
    }

    public Room getRoomByUrl(String id) {
        return roomDao.findRoomById(id);
    }

}
