package davenkin.wanghushengri.brithday;

import davenkin.wanghushengri.sms.SmsSender;
import davenkin.wanghushengri.user.User;
import davenkin.wanghushengri.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by yteng on 8/30/17.
 */

@Component
public class NotificationManager {
    private static Logger logger = LoggerFactory.getLogger(NotificationManager.class);

    private BirthdayRepository birthdayRepository;
    private SmsSender smsSender;
    private UserRepository userRepository;

    @Autowired
    public NotificationManager(BirthdayRepository birthdayRepository,
                               SmsSender smsSender,
                               UserRepository userRepository) {
        this.birthdayRepository = birthdayRepository;
        this.smsSender = smsSender;
        this.userRepository = userRepository;
    }

    //            @Scheduled(fixedDelay = 60000)
    @Scheduled(cron = "0 0 10 * * ?", zone = "Asia/Shanghai")
    public void sendNotifications() {
        List<Birthday> birthdays = birthdayRepository.upcomingBirthdays();

        birthdays.forEach(birthday -> {
            Optional<User> user = userRepository.byId(birthday.getUserId());
            user.ifPresent(theUser -> {
                smsSender.sendBirthdayNotification(theUser.getPhoneNumber(), birthday.smsName(), birthday.smsMessage());
                logger.info("Send {} notification to {}", birthday.toString(), theUser);

            });
        });
        logger.info("=======");
    }

}
