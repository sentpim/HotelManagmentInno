package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zakirskikh.dao.*;
import zakirskikh.model.*;

import java.sql.Date;

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

//        Address address = new Address("Russia", "Innopolis", "Universitetskaya 4, 4-210", "420055");
//
//        address = AddressDao.save(address);
//
//        Person person = new Person("Anvar", "Zakirov", Gender.MALE, "89655969945", "anvarzkr@gmail.com", "9216235343", address.getId());
//
//        person = PersonDao.save(person);
//
//        System.out.println(person);
//
//        person.setFirstName("ROMAN");
//
//        person = PersonDao.save(person);
//
//        Address address = new Address("asdasd", "dsafgasdfg", "sdfasd", "42005sdafsdaf5");
//
//        address = AddressDao.save(address);
//
//        Person person = new Person("ROMAN", "PETUUUUUUH", Gender.MALE, "000000", "kudah@tah.tah", "00000", address.getId());
//
//        person = PersonDao.save(person);

//        Customer customer = new Customer("1234", 1);
//
//        customer = CustomerDao.save(customer);
//
//        System.out.println(customer);
//
//        customer.setPassword("4321");
//
//        customer = CustomerDao.save(customer);
//
//        System.out.println(customer);
//
//        System.out.println(customer.getPerson());

//        Hotel hotel = new Hotel("Hotel#1", 100500, 5, 1);
//        hotel = HotelDao.save(hotel);
//        System.out.println(hotel);

//        Post post = new Post("Cleaning");
//        PostDao.save(post);

//        Employee employee = new Employee(3000, new Date(2016 - (1900), 11 - (1), 16), 3, 1, person.getId());

//        EmployeeDao.save(employee);

        System.out.println("All employees");
        for (Employee e : EmployeeDao.getAll())
            System.out.println(e);

        System.out.println("All posts:");
        for (Post p : PostDao.getAll())
            System.out.println(p);

        System.out.println("All persons:");
        for (Person p : PersonDao.getAll()) {
            System.out.println(p);
            System.out.println(p.getAddress());
        }

        return "user";
    }

    @RequestMapping(value = "bookings" , method = RequestMethod.GET)
    public String getBookings(){
        return "bookings";
    }

    @RequestMapping(value = "employees" , method = RequestMethod.GET)
    public String getEmployees(){
        return "employees";
    }

    @RequestMapping(value = "report" , method = RequestMethod.GET)
    public String getReport(){
        return "report";
    }
}
