package com.simple.project.SimpleBoard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String mainPage() {
        return "form/index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "form/loginPage";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "form/signupPage";
    }
}
