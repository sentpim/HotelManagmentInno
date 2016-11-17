package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zakirskikh.dao.*;
import zakirskikh.form.PersonProfileForm;
import zakirskikh.model.*;

import java.sql.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Anvar on 11/11/2016.
 */
@Controller
public class PersonController {

    @RequestMapping(value = { "/", "/dashboard" }, method = RequestMethod.GET)
    public String getDashdoard(Model model){
        model.addAttribute("is_dashboard_category", true);

        if (!SystemUser.getCurrent().getRole().isAdmin()) {
            return "redirect:/client";
        }

        model.addAttribute("reports", ReportDao.getAll());

        model.addAttribute("is_dashboard_active", "active");
        model.addAttribute("hotel", SystemUser.getCurrent().getEmployee().getHotel());

        return "dashboard";
    }

    @RequestMapping(value = "/user" , method = RequestMethod.GET)
    public String getUser(Model model){
        model.addAttribute("is_user_category", true);

        if (!SystemUser.getCurrent().getRole().isAdmin()) {
            return "redirect:/client";
        }

        model.addAttribute("personProfileForm", new PersonProfileForm());
        SystemUser systemUser = SystemUser.getCurrent();
        model.addAttribute("is_user_active", "active");

        model.addAttribute("user", systemUser.getPerson());
//        model.addAttribute("", systemUser.getPerson().getAddress());
        if (systemUser.getRole().isAdmin()) {
            model.addAttribute("user_employee", systemUser.getEmployee());
        }
        model.addAttribute("user_male", systemUser.getPerson().getGender().isMale());

        return "user";
    }

    @RequestMapping(value =  "/user/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("personProfileForm") PersonProfileForm personProfileForm, Model model){
        System.out.println(personProfileForm);

        PersonDao.save(personProfileForm);

        System.out.println(SystemUser.getCurrent().getPerson());
        System.out.println(SystemUser.getCurrent().getPerson().getAddress());

        return "redirect:/user";
    }

    @RequestMapping(value = "/client" , method = RequestMethod.GET)
    public String getUserRolePage(Model model){
        model.addAttribute("is_user", true);
        model.addAttribute("is_user_category", true);

        model.addAttribute("personProfileForm", new PersonProfileForm());
        SystemUser systemUser = SystemUser.getCurrent();
        model.addAttribute("is_user_active", "active");

        model.addAttribute("user", systemUser.getPerson());
//        model.addAttribute("", systemUser.getPerson().getAddress());
        if (systemUser.getRole().isAdmin()) {
            model.addAttribute("user_employee", systemUser.getEmployee());
        }
        model.addAttribute("user_male", systemUser.getPerson().getGender().isMale());

        return "user";
    }
}
