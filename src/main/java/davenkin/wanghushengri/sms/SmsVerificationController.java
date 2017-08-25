package davenkin.wanghushengri.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SmsVerificationController {
    private SmsVerificationApplicationService smsVerificationApplicationService;

    @Autowired
    public SmsVerificationController(SmsVerificationApplicationService smsVerificationApplicationService) {
        this.smsVerificationApplicationService = smsVerificationApplicationService;
    }

    @PostMapping(value = "/verifications")
    public void sendVerificationCode(@Valid @RequestBody SmsVerificationCommand command) {
        smsVerificationApplicationService.sendVerificationCode(command.getPhoneNumber(), command.getSmsVerificationType());
    }

}
