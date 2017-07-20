package com.drawthink.demo.bean;

/**
 * author：Drawthink
 * describe：
 * date: 2017/5/25
 */

public class ImageBean {
    private String name;
    private int resId;

    public ImageBean(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
