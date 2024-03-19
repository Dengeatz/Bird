package com.example.demo.servingwebcontent;

import com.example.demo.database.UserDB;
import com.example.demo.models.Post;
import com.example.demo.models.Posts;
import com.example.demo.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ProfileController {
    @GetMapping("/user/")
    public String show(Model model) {
        model.addAttribute("error", "Такого пользователя не существует");
        return "error";
    }

    @GetMapping("/user/{name}")
    public String show(@PathVariable("name") String name,Model model) throws SQLException {

        model.addAttribute("user", new UserDB(name).getUser());
        return "profile";
    }
}
