package davenkin.wanghushengri.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by yteng on 8/27/17.
 */

@Profile("local")
@Component
public class StubSmsSender implements SmsSender {
    private static Logger logger = LoggerFactory.getLogger(StubSmsSender.class);

    @Override
    public void sendVerificationCode(PhoneNumber phoneNumber, String code) {
        logger.info("\n\nSent verification code:{} for {}\n\n", code, phoneNumber);
    }

    @Override
    public void sendBirthdayNotification(PhoneNumber phoneNumber, String who, String time) {
        logger.info("\n\nSent birthday sms:{}-{}\n\n", who, time);
    }
}
