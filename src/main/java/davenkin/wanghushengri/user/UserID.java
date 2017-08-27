package davenkin.wanghushengri.user;

/**
 * Created by yteng on 8/25/17.
 */
public final class UserID {
    private final String id;

    private UserID(String id) {
        this.id = id;
    }

    public static UserID of(String id) {
        return new UserID(id);
    }

    public String getId() {
        return id;
    }
}
