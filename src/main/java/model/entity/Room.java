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
    public ArrayList<Message> messages = new ArrayList<>();
    public Time time;
    private String provider;
    private String videoId;
    public CountUsers countUsers;
    public PlayStatusWork playStatusWork;
    public long firstPlayTime;

    public Room(String roomUrl, String provider, String videoId, PlayStatus playStatus, Long time, int countUsers) {
        this.roomUrl = roomUrl;
        this.provider = provider;
        this.videoId = videoId;
        this.time = new Time (time);
        this.countUsers = new CountUsers(countUsers);
        this.playStatusWork = new PlayStatusWork(playStatus);
    }



    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public String getRoomUrl() {
        return roomUrl;
    }

    public String getProvider() { return provider; }

    public String getVideoId() { return videoId; }

    public List<Message> getMessages() {
        return messages;
    }

    public long getFirstPlayTime() {
        return firstPlayTime;
    }

    public void setFirstPlayTime(long firstPlayTme) {
        this.firstPlayTime = firstPlayTme;
    }

    @Override
    public int compareTo(Room room) {
        return this.roomUrl.compareTo(room.getRoomUrl());
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomUrl='" + roomUrl + '\'' +
                ", messages=" + messages +
                '}';
    }
}
