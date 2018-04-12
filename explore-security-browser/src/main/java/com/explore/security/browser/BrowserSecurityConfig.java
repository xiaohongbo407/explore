package com.explore.security.browser;

import com.explore.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.explore.security.core.properties.SecurityProperties;
import com.explore.security.core.validate.code.SmsCodeFilter;
import com.explore.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Created by xiaohb on 2018/1/8.
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler exploreAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler exploreAuthenticationFailureHandler;


    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //开启下面语句，将自动创建token的存储表
        //tokenRepository.setCreateTableOnStartup(true);
        return  tokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.httpBasic()
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(exploreAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(exploreAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        //TODO 修改配置
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                //登录页配置
                //.loginPage("/authentication/require")
                //登录成功和失败配置
                .loginProcessingUrl("/authentication/form")
                    .successHandler(exploreAuthenticationSuccessHandler)
                    .failureHandler(exploreAuthenticationFailureHandler)
                //记住我配置
                .and().rememberMe()
                        .tokenRepository(persistentTokenRepository())
                        .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                        .userDetailsService(userDetailsService)
                //认证请求配置
                .and().authorizeRequests()
                    //配置不需要认证的URL规则
                    .antMatchers("/authentication/require","/authentication/mobile",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/*").permitAll()
                    //任何请求都需要认证
                    .anyRequest().authenticated()
                //跨站请求伪造 禁用
                .and().csrf().disable()
        .apply(smsCodeAuthenticationSecurityConfig);

    }
}
