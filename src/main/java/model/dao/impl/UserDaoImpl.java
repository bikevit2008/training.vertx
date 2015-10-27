package model.dao.impl;

import model.dao.UserDao;
import model.dao.factory.DbService;
import model.entity.User;
import org.mapdb.BTreeMap;
import service.UserService;

/**
 * Created by denis on 13/10/15.
 */
public class UserDaoImpl implements UserDao {

    private BTreeMap<String, User> users = DbService.getUsersTreeMap();

    @Override
    public User insertUser(User user) {
        User result = users.putIfAbsent(user.getId(), user);
        DbService.commitUser();
        return result;
    }

    @Override
    public User removeUser(User user) {
        User result = users.remove(user.getId());
        DbService.commitUser();
        return result;
    }

    @Override
    public User removeUserById(String id) {
        User result = users.remove(id);
        DbService.commitUser();
        return result;
    }

    @Override
    public User updateUser(User user) {
        User result = users.replace(user.getId(), user);
        DbService.commitUser();
        return result;
    }

    @Override
    public User findUserById(String id) {
        return users.get(id);
    }
}
