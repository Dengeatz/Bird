package com.example.demo.servingwebcontent;
import com.example.demo.models.Post;
import com.example.demo.models.Posts;
import com.example.demo.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class LoginController {

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public void login(@ModelAttribute("user") User user, Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("username", user.loginToAccount(user)[0]);
        response.sendRedirect("/");
    }

    @GetMapping("/login")
    public String register(Model model) {
        model.addAttribute("user",  new User());
        return "login";
    }
}
