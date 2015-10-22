package service;

import model.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.factory.ServiceFactory;

import static org.junit.Assert.*;

/**
 * Created by denis on 21/10/15.
 */
public class UserServiceTest {

    UserService userService = ServiceFactory.getUserService();
    String firstUserId = "firstUserId";
    ;
    String secondUserId = "secondUserId";
    User firstUser = new User(firstUserId);
    User secondUser = new User(secondUserId);

    @Before
    public void setUp() {
        userService.removeUser(firstUser);
        userService.removeUser(secondUser);
    }

    @After
    public void tearDown() {
        firstUserId = null;
        secondUserId = null;
        firstUser = null;
        secondUser = null;
    }

    @Test
    public void testAddUser() throws Exception {
        User user = userService.addUser(firstUserId);
        assertNull(user);
        assertEquals(firstUserId, userService.getUserById(firstUserId).getId());
    }

    @Test
    public void testAddUser1() throws Exception {
        User user = userService.addUser(firstUser);
        assertNull(user);
        assertEquals(firstUser, userService.getUserById(firstUser.getId()));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = userService.addUser(firstUser);
        assertNull(user);
        assertEquals(firstUser, userService.getUserById(firstUser.getId()));
    }

    @Test
    public void testRemoveUser() throws Exception {
        User user = userService.addUser(firstUserId);
        assertNull(user);
        User returnedUser = userService.getUserById(firstUserId);
        assertEquals(firstUserId, returnedUser.getId());
        User removedUser = userService.removeUser(returnedUser);
        assertEquals(removedUser, returnedUser);
        assertNull(userService.getUserById(firstUserId));
    }

    @Test
    public void testRemoveUserById() throws Exception {
        User user = userService.addUser(firstUserId);
        assertNull(user);
        User returnedUser = userService.getUserById(firstUserId);
        assertEquals(firstUserId, returnedUser.getId());
        User removedUser = userService.removeUserById(returnedUser.getId());
        assertEquals(removedUser, returnedUser);
        assertNull(userService.getUserById(firstUserId));
    }
}