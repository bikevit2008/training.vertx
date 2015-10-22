package service;

import model.entity.User;

/**
 * Created by denis on 21/10/15.
 */
public interface UserService {

    User updateUser(User user);

    User addUser(String id);

    User addUser(String id, String nickName);

    User addUser(User user);

    User getUserById(String id);

    User removeUser(User user);

    User removeUserById(String id);

}
