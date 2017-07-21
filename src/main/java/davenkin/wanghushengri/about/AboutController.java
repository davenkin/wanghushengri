package davenkin.wanghushengri.about;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

@RestController
public class AboutController {

    //now
    private Date deployTime = new Date();

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
}
