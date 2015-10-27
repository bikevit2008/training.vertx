package model.entity;

import java.io.Serializable;

/**
 * Created by Игорь on 28.10.2015.
 */
public class Time implements Serializable {

    private Long time;

    public Time (Long time) {

        this.time = time;
    }

    public Long getTime() {
        return time;
    }

    public void setPlayStatus(Long time) {
        this.time = time;
    }

}
