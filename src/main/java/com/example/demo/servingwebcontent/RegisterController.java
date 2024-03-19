package com.example.demo.servingwebcontent;


import com.example.demo.Mudak;
import com.example.demo.database.UserDB;
import com.example.demo.models.Post;
import com.example.demo.models.Posts;
import com.example.demo.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.List;


@Controller
@Component
public class RegisterController implements Mudak {
    @Autowired
    UserDB userDB;

    @PostMapping(value="/register")
    public String create(@Valid User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userDB.addToDatabase(user);
            return "redirect:/";
        } catch (SQLException e) {
            model.addAttribute("error", e);
            return "register";
        }

    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDB().getUser());
        return "register";
    }
}
