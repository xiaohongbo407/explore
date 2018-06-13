package com.explore.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * Created by xiaohbW on 2018/6/13.
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ{

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken,String appId){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID,accessToken);
        String result = getRestTemplate().getForObject(url,String.class);

        log.debug("获取QQ的OpenId结果：{}",result);

        this.openId = StringUtils.substringBetween(result,"\"openid\":\"","\"}");
    }

    /**
     * 获取QQ用户基本信息
     * @return
     * @throws IOException
     */
    @Override
    public QQUserInfo getUserInfo()  {
        String url = String.format(URL_GET_USERINFO,appId,openId);
//        QQUserInfo result = getRestTemplate().getForObject(url,QQUserInfo.class);
        String result = getRestTemplate().getForObject(url,String.class);
        QQUserInfo qqUserInfo = null;
        try {
            qqUserInfo = objectMapper.readValue(result,QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败");
        }
        return qqUserInfo;
    }
}
