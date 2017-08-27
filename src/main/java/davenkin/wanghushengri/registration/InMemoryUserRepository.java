package davenkin.wanghushengri.registration;

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

    public InMemoryUserRepository() {
        this.users.put(PhoneNumber.of("15102822755"), new PrincipalUser(new User(UserID.of("100000"), PhoneNumber.of("15102822755")), "$2a$10$ihOHsjioUeDHPC/4hAruZuXotwUYl3Q7q9UTkrypWvcEQYc5z1x6i"));
    }

    @Override
    public void save(PrincipalUser principalUser) {
        users.put(principalUser.getUser().getPhoneNumber(), principalUser);
    }

    @Override
    public Optional<User> byPhoneNumber(PhoneNumber phoneNumber) {
        Optional<PrincipalUser> principalUser = principalUserByPhoneNumber(phoneNumber);
        return getUser(principalUser);
    }

    private Optional<User> getUser(Optional<PrincipalUser> principalUser) {
        if (principalUser.isPresent()) {
            return Optional.of(principalUser.get().getUser());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> byId(UserID userID) {
        return getUser(principalUserByUserID(userID));
    }

    @Override
    public Optional<PrincipalUser> principalUserByPhoneNumber(PhoneNumber phoneNumber) {
        PrincipalUser principalUser = users.get(phoneNumber);
        return Optional.ofNullable(principalUser);
    }

    @Override
    public Optional<PrincipalUser> principalUserByUserID(UserID userID) {
        return users.values().stream().filter(new Predicate<PrincipalUser>() {
            @Override
            public boolean test(PrincipalUser principalUser) {
                return principalUser.getUser().getId().equals(userID);
            }
        }).findFirst();

    }


}
