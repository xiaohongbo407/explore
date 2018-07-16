package com.explore.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 *
 * @author xiaohb
 * @date 2018-07-04 下午6:00
 **/
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        //TODO 根据社交用户信息默认创建用户并返回用户标识
        return connection.getDisplayName();
    }
}
