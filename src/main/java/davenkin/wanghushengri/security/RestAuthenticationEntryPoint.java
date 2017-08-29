package davenkin.wanghushengri.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yteng on 8/28/17.
 */

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    public RestAuthenticationEntryPoint(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
    }
}
