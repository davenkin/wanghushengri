package davenkin.wanghushengri.verification;

import davenkin.wanghushengri.sms.PhoneNumber;
import davenkin.wanghushengri.sms.VerificationType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by yteng on 8/27/17.
 */
public final class VerificationCode {
    private static final int MAX_VALID_MINS = 30;
    private final String code;
    private final LocalDateTime createdTime;
    private final PhoneNumber phoneNumber;
    private final VerificationType verificationType;

    private VerificationCode(String code, PhoneNumber phoneNumber, VerificationType verificationType) {
        this.code = code;
        this.phoneNumber = phoneNumber;
        this.verificationType = verificationType;
        this.createdTime = LocalDateTime.now();
    }

    public boolean isValid() {
        long minutes = ChronoUnit.MINUTES.between(createdTime, LocalDateTime.now());
        return minutes < MAX_VALID_MINS;
    }

    public static VerificationCode of(String code, PhoneNumber phoneNumber, VerificationType verificationType) {
        return new VerificationCode(code, phoneNumber, verificationType);
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public VerificationType getVerificationType() {
        return verificationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerificationCode that = (VerificationCode) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        return verificationType == that.verificationType;

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (verificationType != null ? verificationType.hashCode() : 0);
        return result;
    }
}
