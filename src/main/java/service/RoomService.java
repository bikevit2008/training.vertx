package service;

import model.entity.PlayStatus;
import model.entity.Room;

/**
 * Created by denis on 13/10/15.
 */
public interface RoomService {

    Room addRoom(Room room);

    Room addRoom(String roomUrl, String provider, String videoId, PlayStatus playStatus, Long time, int countUsers);

    Room removeRoom(Room room);

    Room removeRoomById(String id);

    Room updateRoom(Room room);

    Room getRoomByUrl(String id);

}
