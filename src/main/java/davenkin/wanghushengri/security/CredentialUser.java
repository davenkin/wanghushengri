package davenkin.wanghushengri.security;

import davenkin.wanghushengri.user.User;

/**
 * Created by yteng on 8/25/17.
 */
public class CredentialUser {
    private User user;
    private String password;

    public CredentialUser(User user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public User getUser() {
        return user;
    }
}

