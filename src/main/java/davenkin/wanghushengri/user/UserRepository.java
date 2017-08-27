package davenkin.wanghushengri.user;

import davenkin.wanghushengri.sms.PhoneNumber;

import java.util.Optional;

/**
 * Created by yteng on 8/27/17.
 */

public interface UserRepository {

    public Optional<User> byPhoneNumber(PhoneNumber phoneNumber);

    public Optional<User> byId(UserID userID);

}
