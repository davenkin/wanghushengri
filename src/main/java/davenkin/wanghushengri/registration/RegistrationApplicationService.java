package davenkin.wanghushengri.registration;

import davenkin.wanghushengri.sms.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static davenkin.wanghushengri.sms.PhoneNumber.of;

/**
 * Created by yteng on 8/27/17.
 */

@Component
public class RegistrationApplicationService {

    private RegistrationService registrationService;

    @Autowired
    public RegistrationApplicationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void register(RegistrationCommand command) {
        registrationService.registerUser(of(command.getPhoneNumber()), command.getPassword(), command.getPasswordAgain(), command.getVerificationCode());
    }
}
