package zakirskikh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Anvar on 16/11/2016.
 */
@Controller
public class PostController {

    @RequestMapping(value = "/posts" , method = RequestMethod.GET)
    public String getPosts(){
        return "posts";
    }

    @RequestMapping(value = "/posts/add" , method = RequestMethod.GET)
    public String getAddPost(){
        return "post-add";
    }

    @RequestMapping(value = "/posts/add" , method = RequestMethod.POST)
    public String addPost(){
        return "redirect:/posts";
    }

}
