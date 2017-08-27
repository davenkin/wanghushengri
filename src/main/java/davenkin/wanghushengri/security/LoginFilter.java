package davenkin.wanghushengri.security;

import davenkin.wanghushengri.WangObjectMapper;
import davenkin.wanghushengri.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static davenkin.wanghushengri.exception.ErrorResponse.of;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by yteng on 8/26/17.
 */

@Component
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    private WangObjectMapper objectMapper;

    @Autowired
    public LoginFilter(AuthenticationManager authenticationManager, WangObjectMapper objectMapper) {
        super("/login");
        this.objectMapper = objectMapper;
        setAuthenticationManager(authenticationManager);
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

        if (StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(password)) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(phoneNumber, password);

        return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        Claims claims = Jwts.claims().setSubject(user.getId().getId());
        claims.put("scopes", user.getRoles());


        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 7 * 24 * 3600 * 1000);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuer("wanghushengri")
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();


        JwtResponse token = new JwtResponse(jwt);
        response.setStatus(OK.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        response.setStatus(UNAUTHORIZED.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        logger.warn("Login failed:", e);
        objectMapper.writeValue(response.getWriter(), of(UNAUTHORIZED.value(), e.getMessage()));

    }

}
