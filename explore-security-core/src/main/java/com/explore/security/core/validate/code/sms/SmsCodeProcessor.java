package com.explore.security.core.validate.code.sms;

import com.explore.security.core.validate.code.ValidateCode;
import com.explore.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @program: explore
 * @description: 短信验证码处理器
 * @author: XiaoHongBo
 * @create: 2018-03-28 16:48
 **/
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    private SmsCodeSender smsCodeSender;

    /**
     * 短信验证码发送
     * @param request
     * @param validateCode
     * @throws Exception
     */
    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"mobile");
        smsCodeSender.send(mobile,validateCode.getCode());
    }
}
