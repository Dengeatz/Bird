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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;


@Controller
@Component
public class RegisterController implements Mudak {
    @Autowired
    UserDB userDB;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String create(@ModelAttribute("user") @Valid User user, Model model, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "redirect:/register";
        userDB.addToDatabase(user);
        return "redirect:/";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
}
