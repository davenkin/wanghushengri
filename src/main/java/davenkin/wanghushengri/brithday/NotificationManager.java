package davenkin.wanghushengri.brithday;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by yteng on 8/30/17.
 */

@Component
public class NotificationManager {
    private static Logger logger = LoggerFactory.getLogger(NotificationManager.class);

    //    @Scheduled(fixedDelay = 3000)
    @Scheduled(cron = "0 0 10 * * ?", zone = "Asia/Shanghai")
    public void sendNotifications() {
        logger.info("=======");
    }

}
