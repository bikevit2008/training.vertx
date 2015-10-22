package service.impl;

import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.entity.User;
import service.UserService;

/**
 * Created by denis on 21/10/15.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = DaoFactory.getUserDao();

    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public User addUser(String id) {
        if (id == null) throw new IllegalStateException("User id can't be null");
        return userDao.insertUser(new User(id));
    }

    @Override
    public User addUser(String id, String nickName) {
        if (id == null) throw new IllegalStateException("User id can't be null");
        if (nickName == null) throw new IllegalStateException("Don't use empty nick name");
        return userDao.insertUser(new User(id, nickName));
    }

    @Override
    public User addUser(User user) {
        if (user.getId() == null) throw new IllegalStateException("User id can't be null");
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
