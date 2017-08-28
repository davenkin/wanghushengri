package davenkin.wanghushengri.sms;

import davenkin.wanghushengri.exception.CommonBadRequestException;
import org.junit.Test;

/**
 * Created by yteng on 8/28/17.
 */
public class PhoneNumberTest {

    @Test
    public void sholdCreatePhoneNumber() {
        PhoneNumber.of("13123456789");
        PhoneNumber.of("14123456789");
        PhoneNumber.of("18123456789");
        PhoneNumber.of("13123456789");
    }

    @Test(expected = CommonBadRequestException.class)
    public void shouldThrowExceptionWithInvalidNumber() {
        PhoneNumber.of("15102822755d");
    }


}