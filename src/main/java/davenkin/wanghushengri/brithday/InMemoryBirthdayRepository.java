package davenkin.wanghushengri.brithday;

import davenkin.wanghushengri.user.UserID;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Created by yteng on 8/30/17.
 */

@Component
public class InMemoryBirthdayRepository implements BirthdayRepository {

    private Map<BirthdayID, Birthday> birthdays = new HashMap<>();


    public InMemoryBirthdayRepository() {
        new Birthday(UserID.of("100000"), "槟芳妈", CalendarType.LUNAR, 1964, 7, 15, LeadTime.TWO_DAYS);
        new Birthday(UserID.of("100001"), "妈妈", CalendarType.LUNAR, 1964, 7, 15, LeadTime.TWO_DAYS);
    }

    @Override
    public void save(Birthday birthday) {
        birthdays.put(birthday.getId(), birthday);
    }

    @Override
    public List<Birthday> upcomingBirthdays() {
        return birthdays.values().stream().filter(Birthday::shouldNotifyToday).collect(toList());
    }
}
