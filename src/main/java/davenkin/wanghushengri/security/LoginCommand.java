package davenkin.wanghushengri.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yteng on 8/27/17.
 */
public class LoginCommand {
    private String phoneNumber;
    private String password;

    @JsonCreator
    public LoginCommand(@JsonProperty("phoneNumber") String phoneNumber,
                        @JsonProperty("password") String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
