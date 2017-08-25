package davenkin.wanghushengri.registration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yteng on 8/25/17.
 */

@RestController
public class RegistrationController {

    @PostMapping("/registrations")
    public void register(RegistrationCommand command) {

    }
}
