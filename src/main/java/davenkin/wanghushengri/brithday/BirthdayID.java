package davenkin.wanghushengri.brithday;

import java.util.Objects;

/**
 * Created by yteng on 8/30/17.
 */
public class BirthdayID {

    private final String id;

    private BirthdayID(String id) {
        this.id = id;
    }

    public static BirthdayID of(String id) {
        return new BirthdayID(id);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BirthdayID that = (BirthdayID) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
