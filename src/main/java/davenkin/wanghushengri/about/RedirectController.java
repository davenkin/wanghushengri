package davenkin.wanghushengri.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yteng on 9/9/17.
 */

@Controller
public class RedirectController {

    @RequestMapping(value = "redirect")
    public String redirect(){
        return "redirect:/";
    }
}
