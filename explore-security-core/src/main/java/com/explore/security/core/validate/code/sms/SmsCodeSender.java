package com.explore.security.core.validate.code.sms;

/**
 * Created by xiaohb on 2018/1/12.
 */
public interface SmsCodeSender {

    void send(String mobile,String code);
}

