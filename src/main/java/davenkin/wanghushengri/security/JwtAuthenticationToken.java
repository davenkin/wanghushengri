package davenkin.wanghushengri.security;

import davenkin.wanghushengri.user.UserID;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by yteng on 8/28/17.
 */

public final class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final UserID userID;
    private final JwtToken token;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        JwtAuthenticationToken that = (JwtAuthenticationToken) o;

        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        return token != null ? token.equals(that.token) : that.token == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}
