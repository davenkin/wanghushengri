package davenkin.wanghushengri.brithday;

import davenkin.wanghushengri.user.UserID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

import static davenkin.wanghushengri.TimeUtil.DEFAULT_ZONE;
import static davenkin.wanghushengri.TimeUtil.now;

/**
 * Created by yteng on 7/28/17.
 */
public class Birthday {
    private static Logger logger = LoggerFactory.getLogger(Birthday.class);

    private BirthdayID id;
    private UserID userId;
    private String name;
    private CalendarType calendarType;
    private int year;
    private int month;
    private int date;
    private LeadTime leadTime;

    public Birthday(BirthdayID id,
                    UserID userId,
                    String name,
                    CalendarType calendarType,
                    int year,
                    int month,
                    int date,
                    LeadTime leadTime) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.calendarType = calendarType;
        this.year = year;
        this.month = month;
        this.date = date;
        this.leadTime = leadTime;
    }

    public Birthday(UserID userId,
                    String name,
                    CalendarType calendarType,
                    int year,
                    int month,
                    int date,
                    LeadTime leadTime) {
        this.id = BirthdayID.of(UUID.randomUUID().toString());
        this.userId = userId;
        this.name = name;
        this.calendarType = calendarType;
        this.year = year;
        this.month = month;
        this.date = date;
        this.leadTime = leadTime;
    }

    public boolean shouldNotifyToday() {
        try {
            ZonedDateTime notificationDate = birthday().minusDays(leadTime.getDays());
            return notificationDate.toLocalDate().equals(now().toLocalDate());
        } catch (Exception e) {
            logger.warn("Failed to check birthday notification for {}:{}", id.getId(), e.getMessage());
            return false;
        }
    }

    private ZonedDateTime birthday() {
        if (calendarType.equals(CalendarType.LUNAR)) {
            Date solarBirthday = CalendarUtil.lunarToSolar(year, month, date);
            return ZonedDateTime.ofInstant(solarBirthday.toInstant(), DEFAULT_ZONE);
        } else {
            return ZonedDateTime.of(year, month, date, 0, 0, 0, 0, DEFAULT_ZONE);
        }
    }

    public BirthdayID getId() {
        return id;
    }
}
