package davenkin.wanghushengri.sms;

/**
 * Created by yteng on 8/25/17.
 */
public class PhoneNumber {
    private String phoneNumber;

    private PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static PhoneNumber of(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
