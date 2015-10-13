package model.dao;

import model.entity.Room;

/**
 * Created by denis on 13/10/15.
 */
public interface RoomDao {

    boolean insertRoom(Room room);

    boolean removeRoom(Room room);

    boolean removeRoomById(int id);

    Room updateRoom(Room room);

    Room findRoomById(int id);


}
