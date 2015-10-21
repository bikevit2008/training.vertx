package model.dao;

import model.entity.Room;

/**
 * Created by denis on 13/10/15.
 */
public interface RoomDao {

    Room insertRoom(Room room);

    Room removeRoom(Room room);

    Room removeRoomById(int id);

    Room updateRoom(Room room);

    Room findRoomById(int id);


}
