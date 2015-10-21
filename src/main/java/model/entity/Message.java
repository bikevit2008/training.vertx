package model.entity;

import java.io.Serializable;

/**
 * Created by denis on 13/10/15.
 */
public class Message implements Serializable {

    private int id;
    private String text;
    private User owner;

    public Message(int id, String text, User owner) {
        this.id = id;
        this.text = text;
        this.owner = owner;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getId() {

        return id;
    }

    public String getText() {
        return text;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", owner=" + owner +
                '}';
    }
}
