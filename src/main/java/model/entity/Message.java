package model.entity;

import java.io.Serializable;

/**
 * Created by denis on 13/10/15.
 */
public class Message implements Serializable {

    private int id;
    private String message;
    private String nickName;

    public Message(int id, String message, String nickName) {
        this.id = id;
        this.message = message;
        this.nickName = nickName;
    }

    public void setText(String message) {
        this.message = message;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getId() {

        return id;
    }

    public String getText() {
        return message;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + message + '\'' +
                ", nickname=" + nickName +
                '}';
    }
}
