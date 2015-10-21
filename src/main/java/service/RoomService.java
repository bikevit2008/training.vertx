package service;

import model.entity.Message;
import model.entity.Room;
import model.entity.User;

import java.util.List;

/**
 * Created by denis on 13/10/15.
 */
public interface RoomService {

    Room addRoom(int id);

    Room addRoom(int id, List<User> users);

    Room addRoom(int id, List<User> users, List<Message> messages);

    Room removeRoom(Room room);

    Room removeRoomById(int id);

    Room updateRoom(Room room);

    Room getRoomById(int id);

}
