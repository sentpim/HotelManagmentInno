package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import zakirskikh.dao.HotelDao;
import zakirskikh.model.Hotel;

/**
 * Created by Anvar on 16/11/2016.
 */
@Controller
public class HotelController {

    @RequestMapping(value = "/hotels" , method = RequestMethod.GET)
    public String getHotels(){
        return "hotels";
    }

    @RequestMapping(value = "/hotels/add" , method = RequestMethod.GET)
    public String getAddHotel(){
        return "hotel-add";
    }

    @RequestMapping(value = "/hotels/add" , method = RequestMethod.POST)
    public String addHotel(){
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

}
