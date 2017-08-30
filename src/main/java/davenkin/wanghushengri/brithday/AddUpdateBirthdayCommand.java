package davenkin.wanghushengri.brithday;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Created by yteng on 8/28/17.
 */
public class AddUpdateBirthdayCommand {
    @NotNull
    private String userId;

    @NotBlank
    @Length(max = 20, message = "称呼名字不能超过20个字符")
    private String name;

    @NotNull
    private CalendarType calendarType;

    @Range(max = 2049, message = "非法年份")
    private int year;

    @Range(min = 1, max = 12, message = "非法月份")
    private int month;


    @Range(min = 1, max = 31, message = "非法日期")
    private int day;

    @NotNull
    private LeadTime leadTime;

    @JsonCreator
    public AddUpdateBirthdayCommand(@JsonProperty("userId") String userId,
                                    @JsonProperty("name") String name,
                                    @JsonProperty("calendarType") CalendarType calendarType,
                                    @JsonProperty(value = "year") int year,
                                    @JsonProperty("month") int month,
                                    @JsonProperty("day") int day,
                                    @JsonProperty("leadTime") LeadTime leadTime) {

        this.userId = userId;
        this.name = name;
        this.calendarType = calendarType;
        this.year = year;
        this.month = month;
        this.day = day;
        this.leadTime = leadTime;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public CalendarType getCalendarType() {
        return calendarType;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public LeadTime getLeadTime() {
        return leadTime;
    }
}
