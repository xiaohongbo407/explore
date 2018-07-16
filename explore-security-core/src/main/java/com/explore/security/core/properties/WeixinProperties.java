package com.explore.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author xiaohb
 * @date 2018-07-05 上午10:03
 **/
public class WeixinProperties extends SocialProperties{

    /**
     * 第三方ID，用来决定发起第三方登录的url，默认是weixin
     */
    private String providerId = "weixin";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
