package davenkin.wanghushengri.sms;

import davenkin.wanghushengri.exception.CommonBadRequestException;

import java.util.regex.Pattern;

/**
 * Created by yteng on 8/25/17.
 */
public final class PhoneNumber {
    private final String phoneNumber;
    private static final String pattern = "^[1][3,4,5,7,8][0-9]{9}$";

    private PhoneNumber(String phoneNumber) {
        if (!Pattern.matches(pattern, phoneNumber)) {
            throw new CommonBadRequestException("Invalid mobile phone number.");
        }
        this.phoneNumber = phoneNumber;
    }

    public static PhoneNumber of(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        return phoneNumber != null ? phoneNumber.equals(that.phoneNumber) : that.phoneNumber == null;

    }

    @Override
    public int hashCode() {
        return phoneNumber != null ? phoneNumber.hashCode() : 0;
    }
}
