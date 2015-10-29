package model.entity;

import java.io.Serializable;

/**
 * Created by Игорь on 28.10.2015.
 */
public class Time implements Serializable {
    public Time() {
    }


    private double time;

    public Time (double time) {

        this.time = time;
    }

    public double getTime() {
        return time;
    }


    public void setTime(double time) {
        this.time = time;
    }
}
