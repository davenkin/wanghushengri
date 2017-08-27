package davenkin.wanghushengri.security;

import davenkin.wanghushengri.sms.PhoneNumber;
import davenkin.wanghushengri.user.UserID;

import java.util.Optional;

/**
 * Created by yteng on 8/27/17.
 */
public interface CredentialUserRepository {

    public void save(CredentialUser credentialUser);

    public Optional<CredentialUser> principalUserByPhoneNumber(PhoneNumber phoneNumber);

    public Optional<CredentialUser> principalUserByUserID(UserID userID);

}
