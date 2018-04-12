package com.explore.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by xiaohb on 2018/1/10.
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
