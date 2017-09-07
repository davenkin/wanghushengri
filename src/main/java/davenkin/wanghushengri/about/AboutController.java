package davenkin.wanghushengri.about;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

@RestController
public class AboutController {

    //now
    private ZonedDateTime deployTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));

    @Autowired
    private Environment environment;

    @GetMapping(value = "/about")
    public AboutInfo about() {
        String buildNumber = environment.getProperty("buildNumber");
        String buildTime = environment.getProperty("buildTime");
        String activeProfiles = Arrays.toString(environment.getActiveProfiles());
        String deployTime = this.deployTime.toString();
        return new AboutInfo(buildNumber, buildTime, activeProfiles, deployTime);
    }


    @GetMapping(value = "/validate")
    public String validate(@RequestParam("echostr") String echostr) {
        return echostr;
    }

    @PostMapping(value= "/validate")
    public String doPost(@RequestBody String request) {
        System.out.println(request);
        String a ="<xml>\n" +
                "                <ToUserName><![CDATA[o_s1c0qSshYj5Kru95hXtG_5Cmsw]]></ToUserName>\n" +
                "        <FromUserName><![CDATA[gh_b92c0eab3962]]></FromUserName>\n" +
                "        <CreateTime>1504764225</CreateTime>\n" +
                "        <MsgType><![CDATA[text]]></MsgType>\n" +
                "        <Content><![CDATA[你好]]></Content>\n" +
                "        <FuncFlag>0</FuncFlag>\n" +
                "        </xml>";

        return a;
    }


}
