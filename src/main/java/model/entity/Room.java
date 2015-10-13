package model.entity;

import java.util.List;

/**
 * Created by denis on 13/10/15.
 */
public class Room {

    private int id;
    private List<User> users;
    private List<Message> messages;

    public Room(int id) {
        this.id = id;
    }

    public Room(int id, List<User> users) {
        this.id = id;
        this.users = users;
    }

    public Room(int id, List<User> users, List<Message> messages) {
        this.id = id;
        this.users = users;
        this.messages = messages;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public boolean removeUser(User user) {
        return this.users.remove(user);
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public int getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
