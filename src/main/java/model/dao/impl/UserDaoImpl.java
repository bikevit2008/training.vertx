package model.dao.impl;

import model.dao.UserDao;
import model.dao.factory.DbMapFactory;
import model.entity.User;
import org.mapdb.BTreeMap;

/**
 * Created by denis on 13/10/15.
 */
public class UserDaoImpl implements UserDao {

    BTreeMap<String, User> users = DbMapFactory.getUsersTreeMap();

    @Override
    public User insertUser(User user) {
        return users.putIfAbsent(user.getId(), user);
    }

    @Override
    public User removeUser(User user) {
        return users.remove(user.getId());
    }

    @Override
    public User removeUserById(String id) {
        return users.remove(id);
    }

    @Override
    public User updateUser(User user) {
        return users.replace(user.getId(), user);
    }

    @Override
    public User findUserById(String id) {
        return users.get(id);
    }
}
