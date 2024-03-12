package com.example.demo.servingwebcontent;

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
    @GetMapping("/profile")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "profile";
    }
}
