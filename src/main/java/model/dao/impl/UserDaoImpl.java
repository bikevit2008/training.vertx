package model.dao.impl;

import model.dao.UserDao;
import model.entity.User;

/**
 * Created by denis on 13/10/15.
 */
public class UserDaoImpl implements UserDao {

    @Override
    public boolean insertUser(User user) {
        return false;
    }

    @Override
    public boolean removeUser(User user) {
        return false;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User findUserById(String id) {
        return null;
    }
}
