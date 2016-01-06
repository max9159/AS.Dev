package com.yee.launch.lunchorder;

/**
 * Created by ccsnotebook on 2016/1/6.
 */
public class CardItem {    private int backgroundID;
    private String name;

    public int getBackgroundID() {
        return backgroundID;
    }

    public CardItem setBackgroundID(int backgroundID) {
        this.backgroundID = backgroundID;
        return this;
    }

    public String getName() {
        return name;
    }

    public CardItem setName(String name) {
        this.name = name;
        return this;
    }

}
