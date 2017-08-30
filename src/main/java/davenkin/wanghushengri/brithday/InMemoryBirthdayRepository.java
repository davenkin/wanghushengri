package davenkin.wanghushengri.brithday;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yteng on 8/30/17.
 */
public class InMemoryBirthdayRepository implements BirthdayRepository {

    private Map<BirthdayID, Birthday> birthdays = new HashMap<>();

    @Override
    public void save(Birthday birthday) {
        birthdays.put(birthday.getId(), birthday);
    }

    @Override
    public List<Birthday> upcomingBirthdays() {
        return null;
    }
}
