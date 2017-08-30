package davenkin.wanghushengri.verification;

import davenkin.wanghushengri.sms.PhoneNumber;
import davenkin.wanghushengri.sms.SmsProperties;
import davenkin.wanghushengri.sms.SmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yteng on 8/25/17.
 */

@Component
public class VerificationCodeService {
    private static Logger logger = LoggerFactory.getLogger(VerificationCodeService.class);
    private SmsSender smsSender;
    private VerificationCodeGenerator verificationCodeGenerator;
    private VerificationCodeRepository verificationCodeRepository;
    private SmsProperties smsProperties;

    @Autowired
    public VerificationCodeService(SmsSender smsSender,
                                   VerificationCodeGenerator verificationCodeGenerator,
                                   VerificationCodeRepository verificationCodeRepository,
                                   SmsProperties smsProperties) {
        this.smsSender = smsSender;
        this.verificationCodeGenerator = verificationCodeGenerator;
        this.verificationCodeRepository = verificationCodeRepository;
        this.smsProperties = smsProperties;
    }

    public void sendVerificationCode(PhoneNumber phoneNumber, VerificationType verificationType) {
        List<VerificationCode> codesSentToday = verificationCodeRepository.createdToday(phoneNumber, verificationType);

        if (codesSentToday.size() >= smsProperties.getMaxVerifications()) {
            logger.warn("Verifications reached maximum for {}.", phoneNumber);
            return;
        }

        VerificationCode verificationCode = verificationCodeGenerator.generate(phoneNumber, verificationType);
        smsSender.sendVerificationCode(phoneNumber, verificationCode.getCode());
        verificationCodeRepository.save(verificationCode);

    }

}
