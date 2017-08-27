package davenkin.wanghushengri.sms;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by yteng on 7/28/17.
 */

@Profile("production")
@Component
public class AlidayuSmsSender implements SmsSender {

    private SmsProperties smsProperties;

    private TaobaoClient taobaoClient;

    @Autowired
    public AlidayuSmsSender(SmsProperties smsProperties) {
        this.smsProperties = smsProperties;
        taobaoClient = new DefaultTaobaoClient(this.smsProperties.getServerUrl(), this.smsProperties.getAppKey(), this.smsProperties.getAppSecret());
    }

    public void sendVerificationCode(PhoneNumber phoneNumber, String code) {
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        request.setSmsType("normal");
        request.setSmsFreeSignName("忘乎生日");
        request.setSmsTemplateCode("SMS_79005006");
        request.setRecNum(phoneNumber.getPhoneNumber());
        request.setSmsParamString("{\"number\":\"" + code + "\"}");
        send(request);
    }

    public void sendBirthdayNotification(PhoneNumber phoneNumber, String who, String time) {
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        request.setSmsType("normal");
        request.setSmsFreeSignName("忘乎生日");
//            String s = "大舅(56岁)";
//            String s1 = "阴历3月21日(明天)";
        request.setSmsTemplateCode("SMS_60075355");
        request.setRecNum(phoneNumber.getPhoneNumber());
        request.setSmsParamString("{\"who\":\"" + who + "\",\"time\":\"" + time + "\"}");
        send(request);
    }

    private void send(AlibabaAliqinFcSmsNumSendRequest request) {
        try {
            taobaoClient.execute(request);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

}
