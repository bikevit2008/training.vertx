package model.dao.impl;

import model.dao.MessageDao;
import model.entity.Message;
import model.entity.User;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.List;

/**
 * Created by denis on 13/10/15.
 */
public class MessageDaoImpl implements MessageDao {

    @Override
    public boolean insertMessage(Message message) {
        return false;
    }

    @Override
    public boolean removeMessage(Message message) {
        return false;
    }

    @Override
    public Message updateMessage(Message message) {
        return null;
    }

    @Override
    public Message findMessageById(int id) {
        return null;
    }

    @Override
    public List<Message> findMessagesByUser(User user) {
        return null;
    }
}
