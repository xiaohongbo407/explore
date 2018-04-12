package com.explore.security.core.validate.code;


import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 * Created by xiaohb on 2018/1/11.
 */
public interface ValidateCodeGenerator {
    /**
     * 生成校验码
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);
}
