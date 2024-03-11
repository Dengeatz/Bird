package com.example.demo.servingwebcontent;


import com.example.demo.models.Post;
import com.example.demo.models.Posts;
import com.example.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class RegisterController {
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String create(@ModelAttribute("user") User user) {
        user.addToDatabase(user);
        return "redirect:/";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
}
