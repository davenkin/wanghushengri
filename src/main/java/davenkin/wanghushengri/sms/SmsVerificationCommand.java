package davenkin.wanghushengri.sms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yteng on 8/24/17.
 */
public class SmsVerificationCommand {
    private String phoneNumber;
    private SmsVerificationType smsVerificationType;

    @JsonCreator
    public SmsVerificationCommand(@JsonProperty("phoneNumber") String phoneNumber,
                                  @JsonProperty("smsVerificationType") SmsVerificationType smsVerificationType) {
        this.phoneNumber = phoneNumber;
        this.smsVerificationType = smsVerificationType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SmsVerificationType getSmsVerificationType() {
        return smsVerificationType;
    }
}
