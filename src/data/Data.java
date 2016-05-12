package data;

import java.io.Serializable;

public class Data implements Serializable{
    private int userCount;
    public Data (int userCount) {
        this.userCount = userCount;
    }
    public int getUserCount () {
        return userCount;
    }
}