package davenkin.wanghushengri.user;

import davenkin.wanghushengri.security.CredentialUser;
import davenkin.wanghushengri.security.InMemoryCredentialUserRepository;
import davenkin.wanghushengri.sms.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by yteng on 8/27/17.
 */

@Component
public class InMemoryUserRepository implements UserRepository {

    @Autowired
    private InMemoryCredentialUserRepository credentialUserRepository;

    @Override
    public Optional<User> byPhoneNumber(PhoneNumber phoneNumber) {
        Optional<CredentialUser> principalUser = credentialUserRepository.principalUserByPhoneNumber(phoneNumber);
        return getUser(principalUser);
    }

    @Override
    public Optional<User> byId(UserID userID) {
        return getUser(credentialUserRepository.principalUserByUserID(userID));
    }

    private Optional<User> getUser(Optional<CredentialUser> principalUser) {
        if (principalUser.isPresent()) {
            return Optional.of(principalUser.get().getUser());
        }
        return Optional.empty();
    }


}
