package com.explore.security.core.social.weixin.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * 微信的access_token信息。与标准OAuth2协议不同，微信在获取acccess_token时会返回openId，并没有单独的通过accessToken获取openId的服务
 *
 * 所以在这里继承了标准AccessGrant，添加了openId字段，作为对access_token信息的封装
 *
 * @author xiaohb
 * @date 2018-07-05 上午10:18
 **/
public class WeixinAccessGrant extends AccessGrant {


    private static final long serialVersionUID = 9159549751385532158L;

    private String openId;

    public WeixinAccessGrant(String accessToken) {
        super(accessToken);
    }

    public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
