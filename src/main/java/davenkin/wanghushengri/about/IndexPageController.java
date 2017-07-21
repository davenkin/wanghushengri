package davenkin.wanghushengri.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPageController {

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/app/index.html";
    }
}
