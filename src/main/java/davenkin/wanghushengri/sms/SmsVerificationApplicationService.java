package davenkin.wanghushengri.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static davenkin.wanghushengri.sms.PhoneNumber.of;

/**
 * Created by yteng on 8/24/17.
 */

@Component
public class SmsVerificationApplicationService {

    private SmsService smsService;

    @Autowired
    public SmsVerificationApplicationService(SmsService smsService) {
        this.smsService = smsService;
    }

    public void sendVerificationCode(String phoneNumber, SmsVerificationType smsVerificationType) {
        smsService.sendVerificationCode(of(phoneNumber), smsVerificationType);
    }

}
