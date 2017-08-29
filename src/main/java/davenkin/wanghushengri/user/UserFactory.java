package davenkin.wanghushengri.user;

import davenkin.wanghushengri.sms.PhoneNumber;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static davenkin.wanghushengri.user.Role.COMMON_USER;

/**
 * Created by yteng on 8/27/17.
 */

@Component
public class UserFactory {

    public User createUser(PhoneNumber phoneNumber) {
        UserID userID = UserID.of(UUID.randomUUID().toString());
        return new User(userID, phoneNumber, COMMON_USER);
    }
}
