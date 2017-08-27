package davenkin.wanghushengri.verification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import davenkin.wanghushengri.sms.VerificationType;

/**
 * Created by yteng on 8/24/17.
 */
public class VerificationCommand {
    private String phoneNumber;
    private VerificationType verificationType;

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
}
