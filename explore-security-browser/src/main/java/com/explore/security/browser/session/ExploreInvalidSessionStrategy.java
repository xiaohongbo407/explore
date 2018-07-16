package com.explore.security.browser.session;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaohb
 * @program
 * @description
 * @date 2018-07-16 上午11:31
 **/
public class ExploreInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

    public ExploreInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        onSessionInvalid(request, response);
    }

}
