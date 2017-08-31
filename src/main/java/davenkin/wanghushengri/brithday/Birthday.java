package davenkin.wanghushengri.brithday;

import davenkin.wanghushengri.util.TimeUtil;
import davenkin.wanghushengri.user.UserID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

import static davenkin.wanghushengri.util.TimeUtil.DEFAULT_ZONE;
import static davenkin.wanghushengri.util.TimeUtil.now;

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
    private int day;
    private LeadTime leadTime;

    public Birthday(BirthdayID id,
                    UserID userId,
                    String name,
                    CalendarType calendarType,
                    int year,
                    int month,
                    int day,
                    LeadTime leadTime) {
        validate(calendarType, year, month, day);
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.calendarType = calendarType;
        this.year = year;
        this.month = month;
        this.day = day;
        this.leadTime = leadTime;
    }

    private void validate(CalendarType calendarType, int year, int month, int day) {
        if (year == 0) {
            if (calendarType.equals(CalendarType.LUNAR)) {
                if (day > 30) {
                    throw new RuntimeException("非法农历日期");
                }
            } else {
                try {
                    MonthDay.of(month, day);
                } catch (Exception e) {
                    throw new RuntimeException("非法阳历日期", e);
                }
            }
            return;
        }

        int thisYear = TimeUtil.now().getYear();
        if (year < 1900 || year > thisYear) {
            throw new IllegalArgumentException("非法年份");
        }

        if (calendarType.equals(CalendarType.LUNAR)) {
            CalendarUtil.lunarToSolar(year, month, day);
            return;
        }

        try {
            LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new RuntimeException("非法阳历日期", e);
        }
    }

    public Birthday(UserID userId,
                    String name,
                    CalendarType calendarType,
                    int year,
                    int month,
                    int day,
                    LeadTime leadTime) {
        validate(calendarType, year, month, day);
        this.id = BirthdayID.of(UUID.randomUUID().toString());
        this.userId = userId;
        this.name = name;
        this.calendarType = calendarType;
        this.year = year;
        this.month = month;
        this.day = day;
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
        int thisYear = TimeUtil.now().getYear();
        if (calendarType.equals(CalendarType.LUNAR)) {
            Date solarBirthday = CalendarUtil.lunarToSolar(thisYear, month, day);
            return ZonedDateTime.ofInstant(solarBirthday.toInstant(), DEFAULT_ZONE);
        } else {
            return ZonedDateTime.of(thisYear, month, day, 0, 0, 0, 0, DEFAULT_ZONE);
        }
    }

    public BirthdayID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Birthday{" +
                "id=" + id.toString() +
                '}';
    }

    public UserID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String smsName() {
        if (year != 0) {
            int thisYear = TimeUtil.now().getYear();
            return name + "(" + (thisYear - year) + "岁" + ")";
        }
        return name;
    }

    public String smsMessage() {
        String extra;
        switch (leadTime) {
            case ONE_DAY: {
                extra = "(明天)";
                break;
            }
            case TWO_DAYS: {
                extra = "(后天)";
                break;
            }
            default: {
                extra = "";
            }

        }
        if (calendarType.equals(CalendarType.LUNAR)) {
//            return String.format("农历%d月%d日(公历%d月%d日)", month, day, birthday.getMonthValue(), birthday.getDayOfMonth());
            return String.format("农历%d月%d日%s", month, day, extra);
        } else {
            return String.format("公历%d月%d日%s", month, day, extra);

        }

    }
}
