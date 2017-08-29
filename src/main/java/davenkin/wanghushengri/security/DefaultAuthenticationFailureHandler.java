package davenkin.wanghushengri.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import davenkin.wanghushengri.exception.DeveloperMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static davenkin.wanghushengri.exception.DeveloperMessageUtil.developerMessage;
import static davenkin.wanghushengri.exception.ErrorResponse.of;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by yteng on 8/28/17.
 */

@Component
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static Logger logger = LoggerFactory.getLogger(DefaultAuthenticationFailureHandler.class);
    private ObjectMapper objectMapper;

    @Autowired
    public DefaultAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        response.setStatus(UNAUTHORIZED.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        logger.warn("Login failed:", exception);
        objectMapper.writeValue(response.getWriter(), of(UNAUTHORIZED.value(), exception.getMessage(), developerMessage(exception)));

    }
}
