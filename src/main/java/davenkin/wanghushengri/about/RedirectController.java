package davenkin.wanghushengri.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yteng on 9/9/17.
 */

@Controller
public class RedirectController {

    @RequestMapping(value = "redirect")
    public String redirect(@RequestParam("code") String code) {
        System.out.println("=====" + code);
        return "redirect:/";
    }
}
