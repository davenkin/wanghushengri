package davenkin.wanghushengri.brithday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static davenkin.wanghushengri.user.UserID.of;

/**
 * Created by yteng on 8/30/17.
 */

@Component
public class BirthdayApplicationService {

    private BirthdayRepository birthdayRepository;

    @Autowired
    public BirthdayApplicationService(BirthdayRepository birthdayRepository) {
        this.birthdayRepository = birthdayRepository;
    }

    public void addBirthday(AddUpdateBirthdayCommand command) {
        Birthday birthday = new Birthday(of(command.getUserId()),
                command.getName(),
                command.getCalendarType(),
                command.getYear(),
                command.getMonth(),
                command.getDay(),
                command.getLeadTime());
        birthdayRepository.save(birthday);
    }
}
