package model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by denis on 13/10/15.
 */
public class User implements Serializable, Comparable<User> {

    private String id;
    private String nickName;

    public User(String id) {
        this.id = id;
    }

    public User(String id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public String getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(User user) {
        return getId().compareTo(user.getId());
    }
}
