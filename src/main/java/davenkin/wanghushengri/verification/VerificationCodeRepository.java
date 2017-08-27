package davenkin.wanghushengri.verification;

import davenkin.wanghushengri.sms.PhoneNumber;
import davenkin.wanghushengri.sms.VerificationType;

import java.util.Optional;

/**
 * Created by yteng on 8/27/17.
 */

public interface VerificationCodeRepository {

    public void save(VerificationCode code);

    public Optional<VerificationCode> latestFor(PhoneNumber phoneNumber, VerificationType verificationType);
}
