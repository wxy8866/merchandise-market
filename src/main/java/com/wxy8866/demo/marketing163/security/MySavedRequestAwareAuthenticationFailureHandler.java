package com.wxy8866.demo.marketing163.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wxy8866 on 2017/4/20.
 */
@Component("myFailureHandler")
public class MySavedRequestAwareAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler
{
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException
    {
        logger.debug("Authentication Failure, sending 401 Unauthorized error");

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Authentication Failed: " + exception.getMessage());
    }
}
