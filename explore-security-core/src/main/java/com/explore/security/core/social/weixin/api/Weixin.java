package com.explore.security.core.social.weixin.api;

/**
 * @author xiaohb
 * @date 2018-07-05 上午10:07
 **/
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);
}
