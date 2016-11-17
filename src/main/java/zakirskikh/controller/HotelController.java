package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zakirskikh.dao.*;
import zakirskikh.form.FeatureForm;
import zakirskikh.form.HotelForm;
import zakirskikh.form.RoomForm;
import zakirskikh.form.RoomTypeForm;
import zakirskikh.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anvar on 16/11/2016.
 */
@Controller
public class HotelController {

    @RequestMapping(value = "/hotels" , method = RequestMethod.GET)
    public String getHotels(Model model){
        model.addAttribute("hotels", HotelDao.getAll());

        return "hotels";
    }

    @RequestMapping(value = "/hotels/add" , method = RequestMethod.GET)
    public String getAddHotel(Model model){
        model.addAttribute("hotelForm", new HotelForm());

        return "hotel-add";
    }

    @RequestMapping(value = "/hotels/add" , method = RequestMethod.POST)
    public String addHotel(@ModelAttribute("hotelForm") HotelForm hotelForm){

        HotelDao.save(hotelForm);

        System.out.println(hotelForm);

        return "redirect:/hotels";
    }

    @RequestMapping(value = "/hotels/{hotel_id}" , method = RequestMethod.GET)
    public String getHotel(@PathVariable(value = "hotel_id") Integer hotel_id){
        Hotel hotel = HotelDao.get(hotel_id);
        return "hotel-single";
    }

    @RequestMapping(value = "/hotels/{hotel_id}/edit" , method = RequestMethod.GET)
    public String getEditHotel(@PathVariable(value = "hotel_id") Integer hotel_id){
        Hotel hotel = HotelDao.get(hotel_id);
        return "hotel-add";
    }

    @RequestMapping(value = "/hotels/{hotel_id}/rooms" , method = RequestMethod.GET)
    public String getHotelRooms(@PathVariable(value = "hotel_id") Integer hotel_id, Model model){
        Hotel hotel = HotelDao.get(hotel_id);

        List<Room> roomArrayList = RoomDao.getAll(hotel_id);

        model.addAttribute("rooms", roomArrayList);
        model.addAttribute("hotel", hotel);

        return "hotel-rooms";
    }

    @RequestMapping(value = "/hotels/{hotel_id}/bookings" , method = RequestMethod.GET)
    public String getHotelBookings(@PathVariable(value = "hotel_id") Integer hotel_id, Model model){
        Hotel hotel = HotelDao.get(hotel_id);

        List<Booking> roomArrayList = BookingDao.getAll(hotel_id);

        model.addAttribute("bookings", roomArrayList);
        model.addAttribute("hotel", hotel);

        return "bookings";
    }

    @RequestMapping(value = "/hotels/{hotel_id}/rooms/add" , method = RequestMethod.GET)
    public String addHotelRoomForm(@PathVariable(value = "hotel_id") Integer hotel_id, Model model){
        Hotel hotel = HotelDao.get(hotel_id);

        List<RoomType> roomTypes = RoomTypeDao.getAll(hotel_id);

        model.addAttribute("hotel", hotel);
        model.addAttribute("roomTypes", roomTypes);
        model.addAttribute("roomForm", new RoomForm());

        return "hotel-add-room";
    }

    @RequestMapping(value = "/hotels/{hotel_id}/rooms/add" , method = RequestMethod.POST)
    public String addHotelRoom(@ModelAttribute("roomForm") RoomForm roomForm, @PathVariable(value = "hotel_id") Integer hotel_id){

        RoomDao.save(new Room(
                roomForm.getNumber(),
                roomForm.getRoomTypeId()
        ));

        return "redirect:/hotels/" + hotel_id + "/rooms";
    }

    @RequestMapping(value = "/hotels/{hotel_id}/roomtypes/add" , method = RequestMethod.GET)
    public String addHotelRoomTypeForm(@PathVariable(value = "hotel_id") Integer hotel_id, Model model){
        Hotel hotel = HotelDao.get(hotel_id);

        model.addAttribute("roomTypeForm", new RoomTypeForm());
        model.addAttribute("hotel", hotel);

        return "hotel-add-roomtype";
    }

    @RequestMapping(value = "/hotels/{hotel_id}/roomtypes/add" , method = RequestMethod.POST)
    public String addHotelRoomType(@ModelAttribute("roomTypeForm") RoomTypeForm roomTypeForm, @PathVariable(value = "hotel_id") Integer hotel_id){

        RoomTypeDao.save(new RoomType(
                roomTypeForm.getName(),
                roomTypeForm.getPrice(),
                roomTypeForm.getBedsCount(),
                hotel_id)
        );

        return "redirect:/hotels/";
    }

    @RequestMapping(value = "/hotels/{hotel_id}/features/add" , method = RequestMethod.GET)
    public String addHotelFeatureForm(@PathVariable(value = "hotel_id") Integer hotel_id, Model model){
        Hotel hotel = HotelDao.get(hotel_id);

        List<Feature> features = FeatureDao.getAll(hotel_id);

        model.addAttribute("featureForm", new FeatureForm());
        model.addAttribute("hotel", hotel);

        for (Feature feature : features)
            System.out.println(feature);

        return "hotel-add-feature";
    }

    @RequestMapping(value = "/hotels/{hotel_id}/features/add" , method = RequestMethod.POST)
    public String addHotelFeature(@ModelAttribute("featureForm") FeatureForm featureForm, @PathVariable(value = "hotel_id") Integer hotel_id){

        FeatureDao.save(new Feature(
                featureForm.getName(),
                featureForm.getPrice(),
                featureForm.getHotelId()
        ));

        return "redirect:/hotels";
    }

}
