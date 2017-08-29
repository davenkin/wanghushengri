package davenkin.wanghushengri.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import davenkin.wanghushengri.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static davenkin.wanghushengri.sms.PhoneNumber.of;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * Created by yteng on 8/26/17.
 */

@Component
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    private ObjectMapper objectMapper;

    private JwtService jwtService;


    @Autowired
    public LoginFilter(AuthenticationManager authenticationManager,
                       ObjectMapper objectMapper,
                       JwtService jwtService,
                       AuthenticationFailureHandler authenticationFailureHandler) {
        super("/login");
        this.objectMapper = objectMapper;
        this.jwtService = jwtService;
        setAuthenticationManager(authenticationManager);
        setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (!POST.name().equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported.");
        }

        LoginCommand loginCommand;
        try {
            loginCommand = objectMapper.readValue(request.getReader(), LoginCommand.class);
        } catch (Exception e) {
            throw new AuthenticationServiceException("Not able to parse login request.");
        }

        String phoneNumber = loginCommand.getPhoneNumber();
        String password = loginCommand.getPassword();

        if (isEmpty(phoneNumber) || isEmpty(password)) {
            throw new AuthenticationServiceException("Username or password is not provided");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(of(phoneNumber), password);

        return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        JwtToken jwtToken = jwtService.generateJwtToken(user);
        response.setStatus(OK.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), jwtToken);
        logger.info("{} logged in.", user);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        getFailureHandler().onAuthenticationFailure(request, response, e);
    }

}
