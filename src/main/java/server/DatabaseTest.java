package server;

import model.entity.User;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * Created by denis on 21/10/15.
 */
public class DatabaseTest {

    public static void main(String[] args) {
        String firstUserId = "firstUserId";
        String secondUserId = "secondUserId";
        User secondUser = new User(secondUserId);


        UserService userService = new UserServiceImpl();

        userService.addUser(firstUserId);
        userService.addUser(secondUser);
        System.out.println(userService.getUserById(firstUserId));
        userService.removeUser(secondUser);
        System.out.println(userService.getUserById(secondUserId));
    }

}
