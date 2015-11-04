package model.entity;

import java.io.Serializable;

/**
 * Created by Vitaly on 29.10.15.
 */
public class GotJSON implements Serializable {


    private PlayStatus playStatus;
    private double time;
    private String message;
    private String nickName;
    private String utils;

    public GotJSON() {
    }

    public GotJSON(PlayStatus playStatus, double time, String message, String nickName) {
        this.playStatus = playStatus;
        this.time = time;
        this.message = message;
        this.nickName = nickName;
    }

    public PlayStatus getPlayStatus() {
        return playStatus;
    }


    public double getTime() {
        return time;
    }


    public String getMessage() {
        return message;
    }
    

    public String getNickName() {
        return nickName;
    }


    public String getUtils() {
        return utils;
    }


}
