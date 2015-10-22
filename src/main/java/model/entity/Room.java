package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 13/10/15.
 */
public class Room implements Serializable, Comparable<Room> {

    private String roomUrl;
    private List<User> users = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
    private PlayStatus playStatus;
    private Long time;

    public Room(String roomUrl) {
        this.roomUrl = roomUrl;
    }

    public Room(String roomUrl, List<User> users) {
        this.roomUrl = roomUrl;
        this.users = users;
    }

    public Room(String roomUrl, List<User> users, List<Message> messages) {
        this.roomUrl = roomUrl;
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

    public String getRoomUrl() {
        return roomUrl;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public PlayStatus getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(PlayStatus playStatus) {
        this.playStatus = playStatus;
    }

    @Override
    public int compareTo(Room room) {
        return this.roomUrl.compareTo(room.getRoomUrl());
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomUrl='" + roomUrl + '\'' +
                ", users=" + users +
                ", messages=" + messages +
                '}';
    }
}
