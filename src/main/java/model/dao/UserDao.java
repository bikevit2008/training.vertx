package model.dao;

import model.entity.User;

/**
 * Created by denis on 13/10/15.
 */
public interface UserDao {

    User insertUser(User user);

    User removeUser(User user);

    User removeUserById(String id);

    User updateUser(User user);

    User findUserById(String id);

}
