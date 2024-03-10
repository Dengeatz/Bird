package com.example.demo.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ErrorController {
    @GetMapping("error")
    public String error(Model model) {
        return "error";
    }
}
