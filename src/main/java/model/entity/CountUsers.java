package model.entity;

import java.io.Serializable;

/**
 * Created by Игорь on 27.10.2015.
 */
public class CountUsers implements Serializable {

    private int countUsers = 0;

    public CountUsers (int countUser) {
        this.countUsers=countUser;

    }

    public int getCountUser() {
        return countUsers;
    }

    public void setCountUser(int countUser) {
        this.countUsers = countUser;
    }


}
