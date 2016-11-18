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
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Anvar on 11/11/2016.
 */
@Controller
public class PersonController {

    @RequestMapping(value = { "/rows" }, method = RequestMethod.GET)
    public String getRows(Model model) {

        String[] countries = {
                "Russia",
                "USA",
                "New Zealand",
                "Ukraine",
                "Kazakhstan",
                "Mongolia",
                "China",
                "South Korea",
                "Belarus",
                "France",
                "Germany",
                "Italy",
                "Poland",
                "Hungary",
                "Denmark"
        };

        String[] posts = {
                "Cleaning",
                "Administrator",
                "Chief",
                "Lift master",
                "Security",
                "Repair master"
        };

        RoomTypeDao.save(new RoomType("1", 1000, 2));
        RoomTypeDao.save(new RoomType("2", 4000, 2));
        RoomTypeDao.save(new RoomType("3", 5000, 2));
        RoomTypeDao.save(new RoomType("4", 6000, 5));
        RoomTypeDao.save(new RoomType("5", 7000, 5));

        for (String post : posts)
            PostDao.save(new Post(post));

        for (int i = 1; i <= 100; i++) {
            Address address = new Address(
                    countries[ThreadLocalRandom.current().nextInt(countries.length)],
                    "City#" + i,
                    "Address#" + i,
                    ((int)Math.random() * 100000) + ""
            );
            address = AddressDao.save(address);
            Hotel hotel = new Hotel(
                    "Hotel#" + i,
                    ThreadLocalRandom.current().nextInt(5000),
                    ThreadLocalRandom.current().nextInt(0, 6),
                    address.getId()
            );
            hotel = HotelDao.save(hotel);
            for (int j = 1; j < 200; j++) {
                Address address_worker = new Address(
                        countries[ThreadLocalRandom.current().nextInt(countries.length)],
                        "City#" + i + "_" + j,
                        "Address#" + i + "_" + j,
                        ((int)Math.random() * 100000) + ""
                );
                address_worker = AddressDao.save(address_worker);

                Person person = new Person(
                        "Name#" + i + "_" + j,
                        "Surname#" + i + "_" + j,
                        Gender.getGender(ThreadLocalRandom.current().nextInt(2)),
                        "Phone#" + i + "_" + j,
                        "Email#" + i + "_" + j,
                        "Passport#" + i + "_" + j,
                        address_worker.getId()
                );

                person = PersonDao.save(person);

                Employee employee = new Employee(
                        ThreadLocalRandom.current().nextInt(3000, 20000),
                        Date.valueOf(LocalDate.now()),
                        ThreadLocalRandom.current().nextInt(1, posts.length),
                        hotel.getId(),
                        person.getId()
                );

                EmployeeDao.save(employee);
            }

            for (int k = 1; k < 100; k++) {
                for (int l = 1; l < 6; l++) {
                    Room room = RoomDao.save(new Room(k + "", l, hotel.getId()));

                    for (int t = 1; t < 21; t++) {
                        Booking booking = new Booking(
                                Date.valueOf(LocalDate.now()),
                                Date.valueOf(LocalDate.now()),
                                Date.valueOf(LocalDate.now()),
                                ThreadLocalRandom.current().nextInt(1, 10),
                                false,
                                ThreadLocalRandom.current().nextInt(1, 3),
                                ThreadLocalRandom.current().nextInt(1, 900),
                                hotel.getId(),
                                ThreadLocalRandom.current().nextInt(1, 5)
                        );

                        booking = BookingDao.save(booking);
                    }
                }
            }
        }

        return "redirect:/";
    }

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
