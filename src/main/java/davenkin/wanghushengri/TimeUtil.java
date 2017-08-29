package davenkin.wanghushengri;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by yteng on 8/29/17.
 */
public class TimeUtil {
    public static ZonedDateTime now() {
        return ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
    }
}
