package davenkin.wanghushengri.security;

import davenkin.wanghushengri.user.User;
import davenkin.wanghushengri.user.UserID;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import static davenkin.wanghushengri.security.JwtToken.of;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.util.stream.Collectors.toList;

/**
 * Created by yteng on 8/28/17.
 */

@Component
public class JwtService {
    public static final String SECRET_KEY = "secret";
    public static final String ISSUER = "wanghushengri";
    public static final long TIME_TO_LIVE = 30L * 24L * 3600L * 1000L;
    public static final String SCOPES = "scopes";

    public JwtToken generateJwtToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getId().getId());
        claims.put(SCOPES, user.getRoles());

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + TIME_TO_LIVE);

        String tokenString = Jwts.builder()
                .setClaims(claims)
                .setIssuer(ISSUER)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(HS512, SECRET_KEY)
                .compact();

        return of(tokenString);
    }

    public JwtAuthenticationToken from(JwtToken jwtToken) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken.getToken()).getBody();
        String userId = claims.getSubject();
        List<String> roles = claims.get(SCOPES, List.class);
        List<GrantedAuthority> grantedAuthorities = roles.stream().map((Function<String, GrantedAuthority>) role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(toList());
        JwtAuthenticationToken token = new JwtAuthenticationToken(UserID.of(userId), null, grantedAuthorities);
        token.setAuthenticated(true);
        return token;
    }

}
