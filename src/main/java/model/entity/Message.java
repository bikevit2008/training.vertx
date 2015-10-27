package model.entity;

import java.io.Serializable;

/**
 * Created by denis on 13/10/15.
 */
public class Message implements Serializable {

    private int id;
    private String text;
    private String nickName;

    public Message(int id, String text, String nickName) {
        this.id = id;
        this.text = text;
        this.nickName = nickName;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getId() {

        return id;
    }

    public String getText() {
        return text;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", nickname=" + nickName +
                '}';
    }
}
