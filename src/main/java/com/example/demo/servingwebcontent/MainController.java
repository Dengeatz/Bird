package com.example.demo.servingwebcontent;


import com.example.demo.database.UserDB;
import com.example.demo.models.Post;
import com.example.demo.models.Posts;
import com.example.demo.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller


public class MainController {
    @RequestMapping(value="/logout")
    public String reset(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        return "redirect:/";
    }
    @GetMapping("/")
    public String greeting(Model model) {
        Posts post = new Posts();
        List<Post> result = post.getPosts();
        model.addAttribute("posts", result);
        model.addAttribute("user", new UserDB().getUser());
        return "home";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model) {
        model.addAttribute("user", new UserDB(id).getUser())    ;
        return "home";
    }

}

