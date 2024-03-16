package com.example.demo.servingwebcontent;


import com.example.demo.Mudak;
import com.example.demo.database.UserDB;
import com.example.demo.models.Post;
import com.example.demo.models.Posts;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@Controller
@Component
public class RegisterController implements Mudak {
    @Autowired
    UserDB userDB;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String create(@ModelAttribute("user") User user,Model model) {
        try{

            userDB.addToDatabase(user);
            return "redirect:/";
        } catch(SQLException e) {
            System.out.println(e);
            model.addAttribute("error", e);
            return "/error";
        }
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
}
