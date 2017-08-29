package davenkin.wanghushengri.user;

import davenkin.wanghushengri.sms.PhoneNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yteng on 8/25/17.
 */
public class User {
    private UserID id;
    private PhoneNumber phoneNumber;
    private List<Role> roles = new ArrayList<>();

    public User(UserID id, PhoneNumber phoneNumber, Role... roles) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        Collections.addAll(this.roles, roles);
    }

    public UserID getId() {
        return id;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}
