package davenkin.wanghushengri.brithday;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by yteng on 8/28/17.
 */

@RestController
public class BirthdayController {

    @PreAuthorize("hasRole('COMMON_USER')")
    @PostMapping("/birthdays")
    public ResponseEntity<?> addBirthday(@RequestBody AddBirthdayCommand command) {
        return new ResponseEntity<>(CREATED);
    }
}
