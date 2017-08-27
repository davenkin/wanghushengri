package davenkin.wanghushengri.registration;

import davenkin.wanghushengri.security.PrincipalUser;
import davenkin.wanghushengri.sms.PhoneNumber;
import davenkin.wanghushengri.user.User;
import davenkin.wanghushengri.user.UserID;

import java.util.Optional;

/**
 * Created by yteng on 8/27/17.
 */

public interface UserRepository {
    public void save(PrincipalUser principalUser);

    public Optional<User> byPhoneNumber(PhoneNumber phoneNumber);

    public Optional<User> byId(UserID userID);

    public Optional<PrincipalUser> principalUserByPhoneNumber(PhoneNumber phoneNumber);

    public Optional<PrincipalUser> principalUserByUserID(UserID userID);


}
