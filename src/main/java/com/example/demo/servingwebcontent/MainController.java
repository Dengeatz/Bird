package com.example.demo.servingwebcontent;


import com.example.demo.models.Post;
import com.example.demo.models.Posts;
import com.example.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {



    @GetMapping("/")
    public String greeting(Model model) {
        Posts post = new Posts();
        List<Post> result = post.getPosts();
        model.addAttribute("posts", result);
        model.addAttribute("user", new User());
        return "home";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model) {
        model.addAttribute("user", new User(id))    ;
        return "home";
    }

}

