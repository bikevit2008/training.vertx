package model.dao.impl;

import model.dao.RoomDao;
import model.dao.factory.DbMapFactory;
import model.entity.Room;
import org.mapdb.BTreeMap;

/**
 * Created by denis on 13/10/15.
 */
public class RoomDaoImpl implements RoomDao {

    BTreeMap<Integer, Room> rooms = DbMapFactory.getRoomsTreeMap();


    @Override
    public Room insertRoom(Room room) {
        return rooms.putIfAbsent(room.getId(), room);
    }

    @Override
    public Room removeRoom(Room room) {
        return rooms.remove(room.getId());
    }

    @Override
    public Room removeRoomById(int id) {
        return rooms.remove(id);
    }

    @Override
    public Room updateRoom(Room room) {
        return rooms.replace(room.getId(), room);
    }

    @Override
    public Room findRoomById(int id) {
        return rooms.get(id);
    }
}
