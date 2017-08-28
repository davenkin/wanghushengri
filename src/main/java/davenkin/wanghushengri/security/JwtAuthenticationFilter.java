package davenkin.wanghushengri.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yteng on 8/26/17.
 */

@Component
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_HEADER = "Bearer ";


    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   AuthenticationFailureHandler authenticationFailureHandler) {
        super("/**");
        setAuthenticationManager(authenticationManager);
        setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String tokenString = extractToken(request);

        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(null, JwtToken.of(tokenString), null);

        return getAuthenticationManager().authenticate(jwtAuthenticationToken);
    }

    private String extractToken(HttpServletRequest request) {
        String authorizationString = request.getHeader(AUTHORIZATION);

        if (authorizationString == null || !authorizationString.startsWith(TOKEN_HEADER)) {
            throw new BadCredentialsException("Authorization header missing or with invalid format.");
        }

        return authorizationString.substring(TOKEN_HEADER.length(), authorizationString.length());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException exception) throws IOException, ServletException {
        getFailureHandler().onAuthenticationFailure(request, response, exception);
    }

}
