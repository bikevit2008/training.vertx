package service;

import model.entity.Message;
import model.entity.Room;
import model.entity.User;

import java.util.List;

/**
 * Created by denis on 13/10/15.
 */
public interface RoomService {

    boolean addRoom(int id);

    boolean addRoom(int id, List<User> users);

    boolean addRoom(int id, List<User> users, List<Message> messages);

    boolean removeRoom(Room room);

    boolean removeRoom(int id);

    Room updateRoom(Room room);

    Room getRoomById(int id);

}
