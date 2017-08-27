package davenkin.wanghushengri.registration;

import davenkin.wanghushengri.exception.CommonBadRequestException;
import davenkin.wanghushengri.security.PrincipalUser;
import davenkin.wanghushengri.sms.PhoneNumber;
import davenkin.wanghushengri.sms.VerificationType;
import davenkin.wanghushengri.user.User;
import davenkin.wanghushengri.user.UserFactory;
import davenkin.wanghushengri.verification.VerificationCode;
import davenkin.wanghushengri.verification.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by yteng on 8/27/17.
 */

@Component
public class RegistrationService {

    private VerificationCodeRepository verificationCodeRepository;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private UserFactory userFactory;

    @Autowired
    public RegistrationService(VerificationCodeRepository verificationCodeRepository,
                               UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               UserFactory userFactory) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userFactory = userFactory;
    }

    public void registerUser(PhoneNumber phoneNumber, String password, String passwordAgain, String verificationCode) {
        if (!password.equals(passwordAgain)) {
            throw new CommonBadRequestException("Password does not match.");
        }

        Optional<VerificationCode> latestFor = verificationCodeRepository.latestFor(phoneNumber, VerificationType.REGISTRATION);
        if (!latestFor.isPresent() || !latestFor.get().getCode().equals(verificationCode) || !latestFor.get().isValid()) {
            throw new CommonBadRequestException("Invalid verification code.");
        }

        PrincipalUser principalUser = new PrincipalUser(userFactory.createUser(phoneNumber), passwordEncoder.encode(password));
        userRepository.save(principalUser);

    }
}
