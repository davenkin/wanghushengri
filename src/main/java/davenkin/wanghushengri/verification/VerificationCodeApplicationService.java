package davenkin.wanghushengri.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static davenkin.wanghushengri.sms.PhoneNumber.of;

/**
 * Created by yteng on 8/24/17.
 */

@Component
public class VerificationCodeApplicationService {

    private VerificationCodeService verificationCodeService;

    @Autowired
    public VerificationCodeApplicationService(VerificationCodeService verificationCodeService) {
        this.verificationCodeService = verificationCodeService;
    }

    public void sendVerificationCode(String phoneNumber, VerificationType verificationType) {
        verificationCodeService.sendVerificationCode(of(phoneNumber), verificationType);
    }

}
