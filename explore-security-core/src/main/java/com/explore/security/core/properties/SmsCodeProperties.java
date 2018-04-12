package com.explore.security.core.properties;

/**
 * Created by xiaohb on 2018/1/10.
 */
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 300;

    private String url;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
