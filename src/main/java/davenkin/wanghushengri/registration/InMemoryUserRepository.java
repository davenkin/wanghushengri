package davenkin.wanghushengri.registration;

import davenkin.wanghushengri.exception.CommonResourceNotFoundException;
import davenkin.wanghushengri.security.PrincipalUser;
import davenkin.wanghushengri.sms.PhoneNumber;
import davenkin.wanghushengri.user.User;
import davenkin.wanghushengri.user.UserID;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by yteng on 8/27/17.
 */

@Component
public class InMemoryUserRepository implements UserRepository {
    private Map<PhoneNumber, PrincipalUser> users = new HashMap<>();

    @Override
    public void save(PrincipalUser principalUser) {
        users.put(principalUser.getUser().getPhoneNumber(), principalUser);
    }

    @Override
    public User byPhoneNumber(PhoneNumber phoneNumber) {
        return principalUserByPhoneNumber(phoneNumber).getUser();
    }

    @Override
    public User byId(UserID userID) {
        return principalUserByUserID(userID).getUser();
    }

    @Override
    public PrincipalUser principalUserByPhoneNumber(PhoneNumber phoneNumber) {
        try {
            return users.get(phoneNumber);
        } catch (NullPointerException e) {
            throw new CommonResourceNotFoundException("User not found with phone number-" + phoneNumber.getPhoneNumber());
        }
    }

    @Override
    public PrincipalUser principalUserByUserID(UserID userID) {
        Optional<PrincipalUser> user = users.values().stream().filter(new Predicate<PrincipalUser>() {
            @Override
            public boolean test(PrincipalUser principalUser) {
                return principalUser.getUser().getId().equals(userID);
            }
        }).findFirst();
        if (user.isPresent()) {
            return user.get();
        }
        throw new CommonResourceNotFoundException("User not found with id-" + userID.getId());

    }


}
