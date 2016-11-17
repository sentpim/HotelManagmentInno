package zakirskikh.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zakirskikh.dao.PersonDao;
import zakirskikh.dao.SystemUserDao;
import zakirskikh.form.SystemUserForm;
import zakirskikh.model.SystemUser;
import zakirskikh.model.SystemUserRole;

/**
 * Created by Anvar on 16/11/2016.
 */
@Controller
public class SystemUserController {

    @RequestMapping(value = "/systemusers" , method = RequestMethod.GET)
    public String getSystemUsers(Model model){
        if (!SystemUser.getCurrent().getRole().equals(SystemUserRole.SUPERUSER)) {
            return "redirect:/dashboard";
        }

        model.addAttribute("is_sysusers_category", true);

        model.addAttribute("systemUsers", SystemUserDao.getAll());

        return "systemusers";
    }

    @RequestMapping(value = "/systemusers/add" , method = RequestMethod.GET)
    public String getAddSystemUser(Model model){
        if (!SystemUser.getCurrent().getRole().equals(SystemUserRole.SUPERUSER)) {
            return "redirect:/dashboard";
        }

        model.addAttribute("is_sysusers_category", true);

        SystemUserForm systemUserForm = new SystemUserForm();
        model.addAttribute("systemUserForm", systemUserForm);
        model.addAttribute("persons", PersonDao.getAll());

        return "systemuser-add";
    }

    @RequestMapping(value = "/systemusers/add" , method = RequestMethod.POST)
    public String addSystemUser(@ModelAttribute("userForm") SystemUserForm systemUserForm){

        SystemUserDao.save(systemUserForm);

        return "redirect:/systemusers";
    }

    @RequestMapping(value = "/systemusers/{system_user_id}/delete" , method = RequestMethod.GET)
    public String deleteSystemUser(@PathVariable("system_user_id") int system_user_id){

        SystemUserDao.delete(system_user_id);

        return "redirect:/systemusers";
    }
    
}
