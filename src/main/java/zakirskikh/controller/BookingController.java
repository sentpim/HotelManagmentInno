package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zakirskikh.dao.BookingDao;
import zakirskikh.form.BookingForm;
import zakirskikh.model.Booking;

import java.util.List;

/**
 * Created by Anvar on 16/11/2016.
 */
@Controller
public class BookingController {

    @RequestMapping(value = "/bookings" , method = RequestMethod.GET)
    public String getBookings(Model model){

        List<Booking> bookingList = BookingDao.getAll();

        model.addAttribute("bookings", bookingList);

        return "bookings";
    }

    @RequestMapping(value = "/bookings/add" , method = RequestMethod.GET)
    public String getAddBooking(){
        return "booking-add";
    }

    @RequestMapping(value = "/bookings/add" , method = RequestMethod.POST)
    public String addBooking(@ModelAttribute("bookingForm") BookingForm bookingForm){



        return "redirect:/bookings";
    }

    @RequestMapping(value = "/bookings/{booking_id}" , method = RequestMethod.GET)
    public String getBooking(@PathVariable(value = "booking_id") Integer booking_id){
        Booking booking = BookingDao.get(booking_id);
        return "booking-single";
    }

    @RequestMapping(value = "/bookings/edit/{booking_id}" , method = RequestMethod.GET)
    public String getEditBooking(@PathVariable(value = "booking_id") Integer booking_id){
        Booking booking = BookingDao.get(booking_id);
        return "booking-add";
    }

    @RequestMapping(value = "/bookings/delete/{booking_id}" , method = RequestMethod.POST)
    public String deleteBooking(@PathVariable(value = "booking_id") Integer booking_id){
        Booking booking = BookingDao.get(booking_id);
        return "redirect:/bookings";
    }
    
}
