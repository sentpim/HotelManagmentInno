package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zakirskikh.dao.AddressDao;
import zakirskikh.dao.PersonDao;
import zakirskikh.model.Address;
import zakirskikh.model.Gender;
import zakirskikh.model.Person;
import zakirskikh.model.SystemUserRole;

/**
 * Created by Anvar on 11/11/2016.
 */
@Controller
public class PersonController {

    @RequestMapping(value = { "/", "dashboard" }, method = RequestMethod.GET)
    public String getDashdoard(Model model){
        model.addAttribute("is_dashboard_active", "active");
        return "dashboard";
    }

    @RequestMapping(value = "user" , method = RequestMethod.GET)
    public String getUser(Model model){
        model.addAttribute("is_user_active", "active");

//        Address address = new Address("Russia", "Innopolis", "Universitetskaya", 1, 0, "420055");
//
//        address = AddressDao.save(address);
//
//        Person person = new Person("Anvar", "Zakirov", Gender.MALE, "89655969945", "anvarzkr@gmail.com", "9216235343", address.getId());
//
//        PersonDao.save(person);
//
//        address = new Address("asdasd", "dsafgasdfg", "sdfasd", 1, 0, "42005sdafsdaf5");
//
//        address = AddressDao.save(address);
//
//        person = new Person("ROMAN", "PETUUUUUUH", Gender.MALE, "000000", "kudah@tah.tah", "00000", address.getId());
//
//        PersonDao.save(person);

        System.out.println("All persons:");
        for (Person p : PersonDao.getAll())
            System.out.println(p.getAddress());

        return "user";
    }

}
