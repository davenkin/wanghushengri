package davenkin.wanghushengri.brithday;

/**
 * Created by yteng on 7/28/17.
 */
public enum LeadTime {
    ONE_DAY(1),
    TWO_DAYS(2),
    THREE_DAYS(3),
    ONE_WEEK(7);

    private final int days;

     LeadTime(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}
