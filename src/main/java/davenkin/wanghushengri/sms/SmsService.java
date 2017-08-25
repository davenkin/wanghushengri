package davenkin.wanghushengri.sms;

import davenkin.wanghushengri.brithday.Birthday;
import org.springframework.stereotype.Component;

/**
 * Created by yteng on 8/25/17.
 */

@Component
public class SmsService {
    public void sendVerificationCode(PhoneNumber phoneNumber, SmsVerificationType smsVerificationType) {

    }

    public void sendBirthdayNotification(PhoneNumber phoneNumber, Birthday birthday) {
    }
}
