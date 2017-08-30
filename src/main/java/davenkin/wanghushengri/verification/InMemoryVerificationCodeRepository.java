package davenkin.wanghushengri.verification;

import davenkin.wanghushengri.sms.PhoneNumber;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by yteng on 8/27/17.
 */
@Component
public class InMemoryVerificationCodeRepository implements VerificationCodeRepository {
    private Set<VerificationCode> codes = new HashSet<>();


    @Override
    public void save(VerificationCode code) {
        codes.add(code);
    }

    @Override
    public Optional<VerificationCode> latestFor(PhoneNumber phoneNumber, VerificationType verificationType) {
        return codes.
                stream().
                filter(code -> code.getPhoneNumber().equals(phoneNumber) && code.getVerificationType().equals(verificationType)).
                max(Comparator.comparing(VerificationCode::getCreatedTime));
    }

    @Override
    public List<VerificationCode> createdToday(PhoneNumber phoneNumber, VerificationType verificationType) {
        return codes.
                stream().
                filter(code -> code.getPhoneNumber().equals(phoneNumber) && code.getVerificationType().equals(verificationType) && code.isCreatedToday()).collect(toList());

    }
}
