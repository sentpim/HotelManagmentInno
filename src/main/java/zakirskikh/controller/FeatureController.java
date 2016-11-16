package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Anvar on 16/11/2016.
 */
@Controller
public class FeatureController {

    @RequestMapping(value = "/features" , method = RequestMethod.GET)
    public String getFeatures(){
        return "features";
    }

    @RequestMapping(value = "/features/add" , method = RequestMethod.GET)
    public String getAddFeature(){
        return "feature-add";
    }

    @RequestMapping(value = "/features/add" , method = RequestMethod.POST)
    public String addFeature(){
        return "redirect:/features";
    }
    
}
