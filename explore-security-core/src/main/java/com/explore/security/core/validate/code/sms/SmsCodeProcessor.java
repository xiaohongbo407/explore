package com.explore.security.core.validate.code.sms;

import com.explore.security.core.properties.SecurityConstants;
import com.explore.security.core.validate.code.ValidateCode;
import com.explore.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @program: explore
 * @description: 短信验证码处理器
 * @author: XiaoHongBo
 * @create: 2018-03-28 16:48
 **/
@Component("smsValidateCodeProcessor")
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
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;

        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),paramName);
        smsCodeSender.send(mobile,validateCode.getCode());
    }
}
