package model.entity;

import java.io.Serializable;

/**
 * Created by Игорь on 27.10.2015.
 */
public class CountUsers implements Serializable {

    private int countUser = 0;

    public CountUsers (int countUser) {
        this.countUser=countUser;

    }

    public int getCountUser() {
        return countUser;
    }

    public void setCountUser(int countUser) {
        this.countUser = countUser;
    }


}
