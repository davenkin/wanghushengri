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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserID userID = (UserID) o;

        return id != null ? id.equals(userID.id) : userID.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return id;
    }
}
