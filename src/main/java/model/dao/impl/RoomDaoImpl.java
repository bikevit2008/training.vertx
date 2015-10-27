package model.dao.impl;

import model.dao.RoomDao;
import model.dao.factory.DbService;
import model.entity.Room;
import org.mapdb.BTreeMap;

/**
 * Created by denis on 13/10/15.
 */
public class RoomDaoImpl implements RoomDao {

    private BTreeMap<String, Room> rooms = DbService.getRoomsTreeMap();


    @Override
    public Room insertRoom(Room room) {
        Room result = rooms.putIfAbsent(room.getRoomUrl(), room);
        DbService.commitRoom();
        return result;
    }

    @Override
    public Room removeRoom(Room room) {
        Room result = rooms.remove(room.getRoomUrl());
        DbService.commitRoom();
        return result;
    }

    @Override
    public Room removeRoomById(String id) {
        Room result = rooms.remove(id);
        DbService.commitRoom();
        return result;

    }

    @Override
    public Room updateRoom(Room room) {
        Room result = rooms.replace(room.getRoomUrl(), room);
        DbService.commitRoom();
        return result;
    }

    @Override
    public Room findRoomById(String id) {
        return rooms.get(id);
    }
}
