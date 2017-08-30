package davenkin.wanghushengri.brithday;

import davenkin.wanghushengri.user.UserID;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static davenkin.wanghushengri.brithday.LeadTime.ONE_DAY;
import static davenkin.wanghushengri.brithday.LeadTime.TWO_DAYS;
import static java.util.stream.Collectors.toList;

/**
 * Created by yteng on 8/30/17.
 */

@Component
public class InMemoryBirthdayRepository implements BirthdayRepository {

    private Map<BirthdayID, Birthday> birthdays = new HashMap<>();


    public InMemoryBirthdayRepository() {
        addBirthday(UserID.of("100000"), "爸爸", CalendarType.LUNAR, 1953, 11, 7, ONE_DAY);
        addBirthday(UserID.of("100000"), "妈妈", CalendarType.LUNAR, 1953, 4, 14, ONE_DAY);
        addBirthday(UserID.of("100000"), "姐姐", CalendarType.LUNAR, 1979, 12, 20, ONE_DAY);
        addBirthday(UserID.of("100000"), "槟芳", CalendarType.LUNAR, 1990, 8, 3, ONE_DAY);
        addBirthday(UserID.of("100000"), "槟芳爸", CalendarType.LUNAR, 1966, 1, 14, ONE_DAY);
        addBirthday(UserID.of("100000"), "槟芳妈", CalendarType.LUNAR, 1968, 7, 15, ONE_DAY);
        addBirthday(UserID.of("100000"), "槟芳哥", CalendarType.LUNAR, 1988, 6, 3, ONE_DAY);

        addBirthday(UserID.of("100001"), "爸爸", CalendarType.LUNAR, 1966, 1, 14, TWO_DAYS);
        addBirthday(UserID.of("100001"), "妈妈", CalendarType.LUNAR, 1968, 7, 15, TWO_DAYS);
        addBirthday(UserID.of("100001"), "哥", CalendarType.LUNAR, 1988, 6, 3, TWO_DAYS);
    }

    private void addBirthday(UserID userID, String name, CalendarType calendarType, int year, int month, int day, LeadTime leadTime) {
        Birthday birthday = new Birthday(userID, name, calendarType, year, month, day, leadTime);
        BirthdayID id = birthday.getId();
        birthdays.put(id, birthday);
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
