package service;

import model.entity.Message;
import model.entity.Room;
import model.entity.User;

import java.util.List;

/**
 * Created by denis on 13/10/15.
 */
public interface RoomService {

    Room addRoom(Room room);

    Room addRoom(String id);

    Room addRoom(String id, List<User> users);

    Room addRoom(String id, List<User> users, List<Message> messages);

    Room removeRoom(Room room);

    Room removeRoomById(String id);

    Room updateRoom(Room room);

    Room getRoomByUrl(String id);

}
