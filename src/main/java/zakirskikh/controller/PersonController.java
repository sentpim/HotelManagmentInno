package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Anvar on 11/11/2016.
 */
@Controller
public class PersonController {

    @RequestMapping(value = { "/", "dashboard" }, method = RequestMethod.GET)
    public String getDashdoard(){
        return "dashboard";
    }

    @RequestMapping(value = "user" , method = RequestMethod.GET)
    public String getUser(){
        return "user";
    }

}
