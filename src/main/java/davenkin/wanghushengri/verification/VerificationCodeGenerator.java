package davenkin.wanghushengri.verification;

import davenkin.wanghushengri.sms.PhoneNumber;
import davenkin.wanghushengri.sms.VerificationType;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by yteng on 8/27/17.
 */

@Component
public class VerificationCodeGenerator {

    public VerificationCode generate(PhoneNumber phoneNumber, VerificationType verificationType) {
        int code = ThreadLocalRandom.current().nextInt(100000, 999999);
        return VerificationCode.of(String.valueOf(code), phoneNumber,verificationType);
    }
}
