package davenkin.wanghushengri.security;

import davenkin.wanghushengri.user.UserID;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by yteng on 8/28/17.
 */

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private UserID userID;
    private JwtToken token;

    public JwtAuthenticationToken(UserID userID, JwtToken token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.userID = userID;
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return userID;
    }
}
