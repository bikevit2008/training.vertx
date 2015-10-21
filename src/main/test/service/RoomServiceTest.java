package service;

import model.entity.Message;
import model.entity.Room;
import model.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import service.impl.RoomServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by denis on 21/10/15.
 */
public class RoomServiceTest {

    RoomService roomService = new RoomServiceImpl();
    static Integer firstRoomId = 1;
    static List<User> users = new ArrayList<>();
    static List<Message> messages = new ArrayList<>();
    static String firstUserId = "1";
    static String secondUserId = "2";

    @BeforeClass
    public static void onlyOnce() {
        users.add(new User(firstUserId));
        users.add(new User(secondUserId));
        messages.add(new Message(1, "Hey!", users.get(0)));
        messages.add(new Message(2, "Hello!", users.get(1)));
    }

    @Before
    public void setUp() throws Exception {
        roomService.removeRoomById(firstRoomId);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddRoom() throws Exception {
        Room room = roomService.addRoom(firstRoomId);
        assertNull(room);
        assertEquals(firstRoomId, roomService.getRoomById(firstRoomId).getId());
    }

    @Test
    public void testAddRoom1() throws Exception {
        Room room = roomService.addRoom(firstRoomId, users);
        assertNull(room);
        assertEquals(firstRoomId, roomService.getRoomById(firstRoomId).getId());
        assertEquals(users, roomService.getRoomById(firstRoomId).getUsers());
    }

    @Test
    public void testAddRoom2() throws Exception {
        Room room = roomService.addRoom(firstRoomId, users, messages);
        assertNull(room);
        assertEquals(firstRoomId, roomService.getRoomById(firstRoomId).getId());
        assertEquals(users, roomService.getRoomById(firstRoomId).getUsers());
        assertEquals(messages, roomService.getRoomById(firstRoomId).getMessages());
    }

    @Test
    public void testRemoveRoom() throws Exception {
        Room room = roomService.addRoom(firstRoomId, users, messages);
        assertNull(room);
        assertEquals(firstRoomId, roomService.getRoomById(firstRoomId).getId());
        roomService.removeRoomById(firstRoomId);
        assertNull(roomService.getRoomById(firstRoomId));
    }

    @Test
    public void testRemoveRoom1() throws Exception {
        Room room = roomService.addRoom(firstRoomId, users, messages);
        assertNull(room);
        Room returnedRoom = roomService.getRoomById(firstRoomId);
        assertEquals(firstRoomId, returnedRoom.getId());
        roomService.removeRoom(returnedRoom);
        assertNull(roomService.getRoomById(firstRoomId));
    }
}