package davenkin.wanghushengri.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by yteng on 8/27/17.
 */

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private JwtService jwtService;

    @Autowired
    public JwtAuthenticationProvider(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        JwtToken jwtToken = (JwtToken) jwtAuthenticationToken.getCredentials();
        try {
            return jwtService.from(jwtToken);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid token.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
