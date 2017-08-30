package davenkin.wanghushengri.brithday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by yteng on 8/28/17.
 */

@RestController
public class BirthdayController {

    private BirthdayApplicationService birthdayApplicationService;

    @Autowired
    public BirthdayController(BirthdayApplicationService birthdayApplicationService) {
        this.birthdayApplicationService = birthdayApplicationService;
    }

    @PostMapping("/birthdays")
    public ResponseEntity<?> addBirthday(@Valid @RequestBody AddUpdateBirthdayCommand command) {
        birthdayApplicationService.addBirthday(command);
        return new ResponseEntity<>(CREATED);
    }
}
