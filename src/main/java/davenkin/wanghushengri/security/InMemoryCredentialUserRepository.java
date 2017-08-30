package davenkin.wanghushengri.security;

import davenkin.wanghushengri.sms.PhoneNumber;
import davenkin.wanghushengri.user.Role;
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
public class InMemoryCredentialUserRepository implements CredentialUserRepository {
    private Map<PhoneNumber, CredentialUser> users = new HashMap<>();

    public InMemoryCredentialUserRepository() {
        this.users.put(PhoneNumber.of("15102822755"), new CredentialUser(new User(UserID.of("100000"), PhoneNumber.of("15102822755"), Role.ADMIN), "$2a$10$ihOHsjioUeDHPC/4hAruZuXotwUYl3Q7q9UTkrypWvcEQYc5z1x6i"));
        this.users.put(PhoneNumber.of("15202895924"), new CredentialUser(new User(UserID.of("100001"), PhoneNumber.of("15202895924"), Role.ADMIN), "$2a$10$ihOHsjioUeDHPC/4hAruZuXotwUYl3Q7q9UTkrypWvcEQYc5z1x6i"));
    }

    @Override
    public void save(CredentialUser credentialUser) {
        users.put(credentialUser.getUser().getPhoneNumber(), credentialUser);
    }

    @Override
    public Optional<CredentialUser> principalUserByPhoneNumber(PhoneNumber phoneNumber) {
        CredentialUser credentialUser = users.get(phoneNumber);
        return Optional.ofNullable(credentialUser);
    }

    @Override
    public Optional<CredentialUser> principalUserByUserID(UserID userID) {
        return users.values().stream().filter(credentialUser -> credentialUser.getUser().getId().equals(userID)).findFirst();

    }
}
