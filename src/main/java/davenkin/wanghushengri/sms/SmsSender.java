package davenkin.wanghushengri.sms;

/**
 * Created by yteng on 8/27/17.
 */
public interface SmsSender {

    public void sendVerificationCode(PhoneNumber phoneNumber, String code);

    public void sendBirthdayNotification(PhoneNumber phoneNumber, String who, String time);

}
