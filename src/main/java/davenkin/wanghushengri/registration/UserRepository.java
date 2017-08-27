package davenkin.wanghushengri.registration;

import davenkin.wanghushengri.security.PrincipalUser;
import davenkin.wanghushengri.sms.PhoneNumber;
import davenkin.wanghushengri.user.User;
import davenkin.wanghushengri.user.UserID;

/**
 * Created by yteng on 8/27/17.
 */

public interface UserRepository {
    public void save(PrincipalUser principalUser);

    public User byPhoneNumber(PhoneNumber phoneNumber);

    public User byId(UserID userID);

    public PrincipalUser principalUserByPhoneNumber(PhoneNumber phoneNumber);

    public PrincipalUser principalUserByUserID(UserID userID);


}
