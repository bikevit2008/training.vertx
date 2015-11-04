package model.entity;

import java.io.Serializable;

/**
 * Created by Vitaly on 03.11.15.
 */
public class WSUser implements Serializable {


    private String textHandlerId;
    private Long ping;

    public WSUser(String textHandlerId, Long ping) {
        this.textHandlerId = textHandlerId;
        this.ping = ping;
    }

    public WSUser() {
    }

    public WSUser(String textHandlerId) {
        this.textHandlerId = textHandlerId;
    }

    public String getTextHandlerId() {
        return textHandlerId;
    }

    public void setTextHandlerId(String textHandlerId) {
        this.textHandlerId = textHandlerId;
    }

    public Long getPing() {
        return ping;
    }

    public void setPing(Long ping) {
        this.ping = ping;
    }

}
