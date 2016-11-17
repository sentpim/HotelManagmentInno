package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zakirskikh.dao.BookingDao;
import zakirskikh.dao.HotelDao;
import zakirskikh.dao.PersonDao;
import zakirskikh.dao.RoomTypeDao;
import zakirskikh.form.BookingForm;
import zakirskikh.model.Booking;
import zakirskikh.model.Person;
import zakirskikh.model.SystemUser;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anvar on 16/11/2016.
 */
@Controller
public class BookingController {

    @RequestMapping(value = "/bookings" , method = RequestMethod.GET)
    public String getBookings(Model model){
        model.addAttribute("is_bookings_category", true);

        if (!SystemUser.getCurrent().getRole().isAdmin()) {
            return "redirect:/client/bookings";
        }

        List<Booking> bookingList = BookingDao.getAll();

        model.addAttribute("bookings", bookingList);

        return "bookings";
    }

    @RequestMapping(value = "/client/bookings" , method = RequestMethod.GET)
    public String getClientBookings(Model model){
        model.addAttribute("is_bookings_category", true);
        model.addAttribute("is_user", true);

        List<Booking> bookingList = BookingDao.getAllByPersonId(SystemUser.getCurrent().getPersonId());

        model.addAttribute("bookings", bookingList);

        return "bookings";
    }

    @RequestMapping(value = "/bookings/add" , method = RequestMethod.GET)
    public String getAddBooking(Model model){
        model.addAttribute("is_bookings_category", true);

        if (!SystemUser.getCurrent().getRole().isAdmin()) {
            return "redirect:/client/bookings/add";
        }

        model.addAttribute("bookingForm", new BookingForm());
        if (SystemUser.getCurrent().getRole().isLinkedToHotel()) {
            model.addAttribute("hotels", SystemUser.getCurrent().getEmployee().getHotel());
        } else {
            model.addAttribute("hotels", HotelDao.getAll());
        }
        if (SystemUser.getCurrent().getRole().isAdmin()) {
            model.addAttribute("persons", PersonDao.getAll());
        } else {
            ArrayList<Person> persons = new ArrayList<>();
            persons.add(SystemUser.getCurrent().getPerson());
            model.addAttribute("persons", persons);
        }

        model.addAttribute("roomTypes", RoomTypeDao.getAll());

        return "booking-add";
    }

    @RequestMapping(value = "/client/bookings/add" , method = RequestMethod.GET)
    public String getAddClientBooking(Model model){
        model.addAttribute("is_bookings_category", true);

        model.addAttribute("bookingForm", new BookingForm());
        model.addAttribute("hotels", HotelDao.getAll());
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(SystemUser.getCurrent().getPerson());
        model.addAttribute("persons", persons);

        model.addAttribute("roomTypes", RoomTypeDao.getAll());

        return "booking-add";
    }

    @RequestMapping(value = "/bookings/add" , method = RequestMethod.POST)
    public String addBooking(@ModelAttribute("bookingForm") BookingForm bookingForm){

        BookingDao.save(bookingForm);

        return "redirect:/bookings";
    }

//    @RequestMapping(value = "/bookings/{booking_id}" , method = RequestMethod.GET)
//    public String getBooking(@PathVariable(value = "booking_id") Integer booking_id){
//        Booking booking = BookingDao.get(booking_id);
//        return "booking-single";
//    }

    @RequestMapping(value = "/bookings/{booking_id}/delete" , method = RequestMethod.GET)
    public String getEditBooking(@PathVariable(value = "booking_id") Integer booking_id){

        BookingDao.delete(booking_id);

        return "redirect:/bookings";
    }

    @RequestMapping(value = "/bookings/{booking_id}/edit" , method = RequestMethod.GET)
    public String deleteBooking(@PathVariable(value = "booking_id") Integer booking_id, Model model){
        model.addAttribute("is_bookings_category", true);

        Booking booking = BookingDao.get(booking_id);

        model.addAttribute("bookingForm", new BookingForm());
        if (SystemUser.getCurrent().getRole().isLinkedToHotel()) {
            model.addAttribute("hotels", SystemUser.getCurrent().getEmployee().getHotel());
        } else {
            model.addAttribute("hotels", HotelDao.getAll());
        }
        if (SystemUser.getCurrent().getRole().isAdmin()) {
            model.addAttribute("persons", PersonDao.getAll());
        } else {
            ArrayList<Person> persons = new ArrayList<>();
            persons.add(SystemUser.getCurrent().getPerson());
            model.addAttribute("persons", persons);
        }

        model.addAttribute("roomTypes", RoomTypeDao.getAll());

        BookingForm bookingForm = new BookingForm(
                booking_id,
                booking.getHotelId(),
                booking.getDays(),
                booking.getPersonCount(),
                booking.getCheckIn(),
                booking.getPersonId(),
                booking.isPayed(),
                booking.getRoomTypeId()
        );

        model.addAttribute("bookingForm", bookingForm);

        return "booking-add";
    }

    @RequestMapping(value = "/bookings/{booking_id}/pay" , method = RequestMethod.GET)
    public String payBooking(@PathVariable(value = "booking_id") Integer booking_id){
        Booking booking = BookingDao.get(booking_id);

        booking.setPayed(true);
        BookingDao.save(booking);

        if (!SystemUser.getCurrent().getRole().isAdmin()) {
            return "redirect:/client/bookings";
        }

        return "redirect:/hotels/" + booking.getHotelId() + "/bookings";
    }
    
}
