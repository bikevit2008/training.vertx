package model.dao.impl;

import model.dao.RoomDao;
import model.dao.factory.DbMapFactory;
import model.entity.Room;
import org.mapdb.BTreeMap;

/**
 * Created by denis on 13/10/15.
 */
public class RoomDaoImpl implements RoomDao {

    BTreeMap<String, Room> rooms = DbMapFactory.getRoomsTreeMap();


    @Override
    public Room insertRoom(Room room) {
        return rooms.putIfAbsent(room.getRoomUrl(), room);
    }

    @Override
    public Room removeRoom(Room room) {
        return rooms.remove(room.getRoomUrl());
    }

    @Override
    public Room removeRoomById(String id) {
        return rooms.remove(id);
    }

    @Override
    public Room updateRoom(Room room) {
        return rooms.replace(room.getRoomUrl(), room);
    }

    @Override
    public Room findRoomById(String id) {
        return rooms.get(id);
    }
}
