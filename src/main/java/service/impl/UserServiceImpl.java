package service.impl;

import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.entity.User;
import service.UserService;

/**
 * Created by denis on 21/10/15.
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = DaoFactory.getUserDao();

    @Override
    public User addUser(String id) {
        return userDao.insertUser(new User(id));
    }

    @Override
    public User addUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public User getUserById(String id) {
        return userDao.findUserById(id);
    }

    @Override
    public User removeUser(User user) {
        return userDao.removeUser(user);
    }

    @Override
    public User removeUserById(String id) {
        return userDao.removeUserById(id);
    }
}
