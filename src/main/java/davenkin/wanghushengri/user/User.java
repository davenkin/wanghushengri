package davenkin.wanghushengri.user;

import davenkin.wanghushengri.sms.PhoneNumber;

/**
 * Created by yteng on 8/25/17.
 */
public class User {
    private UserID id;
    private PhoneNumber phoneNumber;

    public User(UserID id, PhoneNumber phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public UserID getId() {
        return id;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}
