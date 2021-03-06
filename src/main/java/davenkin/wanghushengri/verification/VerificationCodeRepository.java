package davenkin.wanghushengri.verification;

import davenkin.wanghushengri.sms.PhoneNumber;

import java.util.List;
import java.util.Optional;

/**
 * Created by yteng on 8/27/17.
 */

public interface VerificationCodeRepository {

    public void save(VerificationCode code);

    public Optional<VerificationCode> latestFor(PhoneNumber phoneNumber, VerificationType verificationType);

    public List<VerificationCode> createdToday(PhoneNumber phoneNumber, VerificationType verificationType);
}
