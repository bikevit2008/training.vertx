package model.entity;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 13/10/15.
 */
public class Room implements Serializable, Comparable<Room> {
    @JsonIgnore
    private String roomUrl;
    @JsonIgnore
    private List<String> users = new ArrayList<>();
    public List<Message> messages = new ArrayList<>();
    public PlayStatus playStatus;
    public Time time;
    private String provider;
    private String videoId;
    public CountUsers countUsers;
    public PlayStatusWork playStatusWork;

    public Room(String roomUrl, String provider, String videoId, PlayStatus playStatus, Long time, int countUsers) {
        this.roomUrl = roomUrl;
        this.provider = provider;
        this.videoId = videoId;
        this.playStatus = playStatus;
        this.time = new Time (time);
        this.countUsers = new CountUsers(countUsers);
        this.playStatusWork = new PlayStatusWork(playStatus);
    }


    public void setUsers(List<String> users) {
        this.users = users;
    }

    public void addUser(String user) {
        this.users.add(user);
    }

    public boolean removeUser(String user) {
        return this.users.remove(user);
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public String getRoomUrl() {
        return roomUrl;
    }

    public List<String> getUsers() {
        return users;
    }

    public List<Message> getMessages() {
        return messages;
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
