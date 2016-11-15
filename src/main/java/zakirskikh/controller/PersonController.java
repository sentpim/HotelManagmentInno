package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Anvar on 11/11/2016.
 */
@Controller
public class PersonController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(){
        System.out.println(1);
<<<<<<< HEAD
        return "dashboard";
=======
        return "index";
>>>>>>> bdf042ec66c39f9a3275ddca57d1e5c6b996b89a
    }

}
