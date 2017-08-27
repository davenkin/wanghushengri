package davenkin.wanghushengri.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by yteng on 8/25/17.
 */

@RestController
public class RegistrationController {

    private RegistrationApplicationService registrationApplicationService;

    @Autowired
    public RegistrationController(RegistrationApplicationService registrationApplicationService) {
        this.registrationApplicationService = registrationApplicationService;
    }

    @PostMapping("/registrations")
    public ResponseEntity<?> register(@RequestBody RegistrationCommand command) {
        registrationApplicationService.register(command);
        return new ResponseEntity<>(CREATED);

    }
}
