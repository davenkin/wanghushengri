package davenkin.wanghushengri.security;

/**
 * Created by yteng on 8/27/17.
 */
public final class JwtResponse {
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
