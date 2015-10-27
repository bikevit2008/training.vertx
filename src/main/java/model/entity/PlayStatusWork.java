package model.entity;

import java.io.Serializable;

/**
 * Created by Игорь on 28.10.2015.
 */
public class PlayStatusWork implements Serializable {

    private PlayStatus playStatus;

    public PlayStatusWork (PlayStatus playStatus) {
        this.playStatus = playStatus;
    }

    public PlayStatus getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(PlayStatus playStatus) {
        this.playStatus = playStatus;
    }


}
