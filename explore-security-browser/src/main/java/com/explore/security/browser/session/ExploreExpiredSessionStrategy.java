package com.explore.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author xiaohb
 * @program
 * @description
 * @date 2018-07-16 上午11:24
 **/
public class ExploreExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public ExploreExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    /** (non-Javadoc)
     * @see org.springframework.security.web.session.SessionInformationExpiredStrategy#onExpiredSessionDetected(org.springframework.security.web.session.SessionInformationExpiredEvent)
     */
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    /** (non-Javadoc)
     * @see com.explore.security.browser.session.AbstractSessionStrategy#isConcurrency()
     */
    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
