package model.dao;

import model.entity.Message;
import model.entity.User;

import java.util.List;

/**
 * Created by denis on 13/10/15.
 */
public interface MessageDao {

    boolean insertMessage(Message message);

    boolean removeMessage(Message message);

    Message updateMessage(Message message);

    Message findMessageById(int id);

    List<Message> findMessagesByUser(User user);

}
