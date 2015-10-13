package model.dao;

import model.entity.User;

/**
 * Created by denis on 13/10/15.
 */
public interface UserDao {

    boolean insertUser(User user);

    boolean removeUser(User user);

    User updateUser(User user);

    User findUserById(String id);

}
