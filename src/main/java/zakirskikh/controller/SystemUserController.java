package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zakirskikh.form.SystemUserForm;

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
    public String getAddSystemUser(Model model){
        SystemUserForm systemUserForm = new SystemUserForm();
        model.addAttribute("systemUserForm", systemUserForm);

        return "systemUser-add";
    }

    @RequestMapping(value = "/systemUsers/add" , method = RequestMethod.POST)
    public String addSystemUser(@ModelAttribute("userForm") SystemUserForm systemUserForm){


        return "redirect:/systemUsers";
    }
    
}
