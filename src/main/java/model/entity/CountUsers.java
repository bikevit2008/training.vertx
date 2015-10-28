package model.entity;

import java.io.Serializable;

/**
 * Created by Игорь on 27.10.2015.
 */
public class CountUsers implements Serializable {

    private int countUsers = 0;

    public CountUsers (int countUsers) {
        this.countUsers=countUsers;

    }

    public int getCountUsers() {
        return countUsers;
    }

    public void setCountUsers(int countUsers) {
        this.countUsers = countUsers;
    }


}
