package davenkin.wanghushengri.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Created by yteng on 8/25/17.
 */
public final class RegistrationCommand {
    private final String phoneNumber;
    private final String password;
    private final String passwordAgain;
    private final String verificationCode;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationCommand that = (RegistrationCommand) o;
        return Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(password, that.password) &&
                Objects.equals(passwordAgain, that.passwordAgain) &&
                Objects.equals(verificationCode, that.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, password, passwordAgain, verificationCode);
    }
}
