package com.explore.security.core.properties;

/**
 * Created by xiaohb on 2018/1/10.
 */
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        this.setLength(4);
        this.setExpireIn(60);
    }

    private int width = 67;
    private int height= 23;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
