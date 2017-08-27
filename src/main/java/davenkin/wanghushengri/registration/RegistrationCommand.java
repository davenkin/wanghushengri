package davenkin.wanghushengri.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yteng on 8/25/17.
 */
public class RegistrationCommand {
    private String phoneNumber;
    private String password;
    private String passwordAgain;
    private String verificationCode;

    @JsonCreator
    public RegistrationCommand(@JsonProperty("phoneNumber") String phoneNumber,
                               @JsonProperty("password") String password,
                               @JsonProperty("passwordAgain") String passwordAgain,
                               @JsonProperty("verificationCode") String verificationCode) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.passwordAgain = passwordAgain;
        this.verificationCode = verificationCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}
