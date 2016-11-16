package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Anvar on 16/11/2016.
 */
@Controller
public class SystemUserController {

    @RequestMapping(value = "/systemUsers" , method = RequestMethod.GET)
    public String getSystemUsers(){
        return "systemUsers";
    }

    @RequestMapping(value = "/systemUsers/add" , method = RequestMethod.GET)
    public String getAddSystemUser(){
        return "systemUser-add";
    }

    @RequestMapping(value = "/systemUsers/add" , method = RequestMethod.POST)
    public String addSystemUser(){
        return "redirect:/systemUsers";
    }
    
}
