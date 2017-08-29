package davenkin.wanghushengri.registration;

import davenkin.wanghushengri.exception.CommonBadRequestException;
import davenkin.wanghushengri.security.CredentialUser;
import davenkin.wanghushengri.security.CredentialUserRepository;
import davenkin.wanghushengri.sms.PhoneNumber;
import davenkin.wanghushengri.user.UserFactory;
import davenkin.wanghushengri.verification.VerificationCode;
import davenkin.wanghushengri.verification.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

import static davenkin.wanghushengri.verification.VerificationType.REGISTRATION;

/**
 * Created by yteng on 8/27/17.
 */

@Component
public class RegistrationService {

    private static final String PASSWORD_PATTERN = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$";

    private VerificationCodeRepository verificationCodeRepository;

    private CredentialUserRepository credentialUserRepository;

    private PasswordEncoder passwordEncoder;

    private UserFactory userFactory;

    @Autowired
    public RegistrationService(VerificationCodeRepository verificationCodeRepository,
                               CredentialUserRepository credentialUserRepository,
                               PasswordEncoder passwordEncoder,
                               UserFactory userFactory) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.credentialUserRepository = credentialUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.userFactory = userFactory;
    }

    public void registerUser(PhoneNumber phoneNumber, String password, String passwordAgain, String verificationCode) {
        if (!password.equals(passwordAgain)) {
            throw new CommonBadRequestException("Password does not match.");
        }

        if (!Pattern.matches(PASSWORD_PATTERN, password)) {
            throw new CommonBadRequestException("密码必须由6-12位数字和字母组成.");
        }


        Optional<VerificationCode> latestFor = verificationCodeRepository.latestFor(phoneNumber, REGISTRATION);
        if (!latestFor.isPresent() || !latestFor.get().getCode().equals(verificationCode) || !latestFor.get().isValid()) {
            throw new CommonBadRequestException("Invalid verification code.");
        }

        CredentialUser principalUser = new CredentialUser(userFactory.createUser(phoneNumber), passwordEncoder.encode(password));
        credentialUserRepository.save(principalUser);

    }
}
