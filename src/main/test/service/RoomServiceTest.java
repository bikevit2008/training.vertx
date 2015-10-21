package service;

import model.dao.factory.ServiceFactory;
import model.entity.Message;
import model.entity.Room;
import model.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by denis on 21/10/15.
 */
public class RoomServiceTest {

    RoomService roomService = ServiceFactory.getRoomService();
    static String firstRoomUrl = "1";
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
        roomService.removeRoomById(firstRoomUrl);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddRoom() throws Exception {
        Room room = roomService.addRoom(firstRoomUrl);
        assertNull(room);
        assertEquals(firstRoomUrl, roomService.getRoomByUrl(firstRoomUrl).getRoomUrl());
    }

    @Test
    public void testAddRoom1() throws Exception {
        Room room = roomService.addRoom(firstRoomUrl, users);
        assertNull(room);
        assertEquals(firstRoomUrl, roomService.getRoomByUrl(firstRoomUrl).getRoomUrl());
        assertEquals(users, roomService.getRoomByUrl(firstRoomUrl).getUsers());
    }

    @Test
    public void testAddRoom2() throws Exception {
        Room room = roomService.addRoom(firstRoomUrl, users, messages);
        assertNull(room);
        assertEquals(firstRoomUrl, roomService.getRoomByUrl(firstRoomUrl).getRoomUrl());
        assertEquals(users, roomService.getRoomByUrl(firstRoomUrl).getUsers());
        assertEquals(messages, roomService.getRoomByUrl(firstRoomUrl).getMessages());
    }

    @Test
    public void testRemoveRoom() throws Exception {
        Room room = roomService.addRoom(firstRoomUrl, users, messages);
        assertNull(room);
        assertEquals(firstRoomUrl, roomService.getRoomByUrl(firstRoomUrl).getRoomUrl());
        roomService.removeRoomById(firstRoomUrl);
        assertNull(roomService.getRoomByUrl(firstRoomUrl));
    }

    @Test
    public void testRemoveRoomById() throws Exception {
        Room room = roomService.addRoom(firstRoomUrl, users, messages);
        assertNull(room);
        Room returnedRoom = roomService.getRoomByUrl(firstRoomUrl);
        assertEquals(firstRoomUrl, returnedRoom.getRoomUrl());
        roomService.removeRoom(returnedRoom);
        assertNull(roomService.getRoomByUrl(firstRoomUrl));
    }

    @Test
    public void testUpdateRoom() throws Exception {
        Room room = roomService.addRoom(firstRoomUrl, users, messages);
        assertNull(room);
        Room returnedRoom = roomService.getRoomByUrl(firstRoomUrl);
        returnedRoom.getUsers().remove(1);
        Room updatedRoom = roomService.updateRoom(returnedRoom);
        assertEquals(users.size(), updatedRoom.getUsers().size());
    }

    @Test
    public void testGetRoomById() throws Exception {
        Room room = roomService.addRoom(firstRoomUrl, users, messages);
        assertNull(room);
        Room returnedRoom = roomService.getRoomByUrl(firstRoomUrl);
        assertEquals(firstRoomUrl, returnedRoom.getRoomUrl());
    }
}