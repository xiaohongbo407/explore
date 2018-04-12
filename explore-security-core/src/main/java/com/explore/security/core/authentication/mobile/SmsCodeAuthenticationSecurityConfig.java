package com.explore.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @program: explore
 * @description:
 * @author: XiaoHongBo
 * @create: 2018-04-03 09:10
 **/
@Configuration
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //创建短信验证码的拦截器对象
        SmsCodeAuthenticationFilter smsFilter = new SmsCodeAuthenticationFilter();
        smsFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        //统一验证成功和失败的处理器
        smsFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        smsFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        //创建短信验证码的验证逻辑对象
        SmsCodeAuthenticationProvider smsProvider = new SmsCodeAuthenticationProvider();
        smsProvider.setUserDetailsService(userDetailsService);

        //将短信验证码处理逻辑加入到HttpSecurity中，并在用户名和密码拦截器之前添加短信验证码
        http.authenticationProvider(smsProvider)
                .addFilterAfter(smsFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
