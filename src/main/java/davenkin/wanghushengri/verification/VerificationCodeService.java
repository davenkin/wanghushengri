package davenkin.wanghushengri.verification;

import davenkin.wanghushengri.sms.PhoneNumber;
import davenkin.wanghushengri.sms.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yteng on 8/25/17.
 */

@Component
public class VerificationCodeService {
    private SmsSender smsSender;
    private VerificationCodeGenerator verificationCodeGenerator;
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    public VerificationCodeService(SmsSender smsSender,
                                   VerificationCodeGenerator verificationCodeGenerator,
                                   VerificationCodeRepository verificationCodeRepository) {
        this.smsSender = smsSender;
        this.verificationCodeGenerator = verificationCodeGenerator;
        this.verificationCodeRepository = verificationCodeRepository;
    }

    public void sendVerificationCode(PhoneNumber phoneNumber, VerificationType verificationType) {
        VerificationCode verificationCode = verificationCodeGenerator.generate(phoneNumber, verificationType);
        smsSender.sendVerificationCode(phoneNumber, verificationCode.getCode());
        verificationCodeRepository.save(verificationCode);
    }

}
