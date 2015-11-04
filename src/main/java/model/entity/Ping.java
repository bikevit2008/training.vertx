package model.entity;

import java.io.Serializable;

/**
 * Created by Vitaly on 02.11.15.
 */
public class Ping implements Serializable {

    private String utils = "ping";

    public Ping() {
    }

    public String getUtils() {
        return utils;
    }

    public void setUtils(String utils) {
        this.utils = utils;
    }

}
