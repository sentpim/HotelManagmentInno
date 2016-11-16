package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import zakirskikh.form.SystemUserForm;
import zakirskikh.model.SystemUser;

/**
 * Created by Anvar on 16/11/2016.
 */
@Controller
public class AuthController {

    @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
    public String signIn(@RequestParam(value = "error", required = false) Boolean error, Model model) {
        if (Boolean.TRUE.equals(error))
            model.addAttribute("error", error);
        return "sign_in";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public String signInPage(Model model){
        SystemUserForm systemUserForm = new SystemUserForm();
        model.addAttribute("systemUserForm", systemUserForm);

        return "/sign_up";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
//    public String SignUpProcess(@ModelAttribute("userForm") UserForm userForm, BindingResult result){
    public String SignUpProcess(BindingResult result){

        boolean registered = false;

        if (!result.hasErrors()){
            try {
//                userService.registerUser(userForm);
//            }catch (EmailExistsException emailExistsException){
//                result.rejectValue("email", "", "Email already exists!");
            }catch (IllegalArgumentException illegalArgumentException){
                System.err.println(illegalArgumentException.getMessage());
                String fieldName;
                switch (illegalArgumentException.getMessage()){
                    case "email":
                        fieldName = "Email";
                        break;
                    case "password":
                        fieldName = "Password";
                        break;
                    case "firstName":
                        fieldName = "First Name";
                        break;
                    case "lastName":
                        fieldName = "Last Name";
                        break;
                    default:
                        fieldName = "Undefined";
                }
                result.rejectValue(illegalArgumentException.getMessage(), "", fieldName + " field can't be blank!");
            }
        }

        if (result.hasErrors()){
            return "sign_up";
        }else {
            return "redirect:/sign_in";
        }

    }

}
