package davenkin.wanghushengri.brithday;

import java.util.List;

/**
 * Created by yteng on 8/30/17.
 */
public interface BirthdayRepository {
    public void save(Birthday birthday);

    public List<Birthday> upcomingBirthdays();
}
