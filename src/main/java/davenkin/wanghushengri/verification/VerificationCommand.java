package davenkin.wanghushengri.verification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Created by yteng on 8/24/17.
 */
public final class VerificationCommand {
    private final String phoneNumber;
    private final VerificationType verificationType;

    @JsonCreator
    public VerificationCommand(@JsonProperty("phoneNumber") String phoneNumber,
                               @JsonProperty("verificationType") VerificationType verificationType) {
        this.phoneNumber = phoneNumber;
        this.verificationType = verificationType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public VerificationType getVerificationType() {
        return verificationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationCommand that = (VerificationCommand) o;
        return Objects.equals(phoneNumber, that.phoneNumber) &&
                verificationType == that.verificationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, verificationType);
    }
}
