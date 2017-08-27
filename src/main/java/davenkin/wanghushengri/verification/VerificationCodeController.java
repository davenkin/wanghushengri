package davenkin.wanghushengri.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class VerificationCodeController {
    private VerificationCodeApplicationService verificationCodeApplicationService;

    @Autowired
    public VerificationCodeController(VerificationCodeApplicationService verificationCodeApplicationService) {
        this.verificationCodeApplicationService = verificationCodeApplicationService;
    }

    @PostMapping(value = "/verifications")
    public void sendVerificationCode(@Valid @RequestBody VerificationCommand command) {
        verificationCodeApplicationService.sendVerificationCode(command.getPhoneNumber(), command.getVerificationType());
    }

}
