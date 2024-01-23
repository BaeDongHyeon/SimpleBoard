package com.simple.project.SimpleBoard.controller;

import com.simple.project.SimpleBoard.domain.form.PostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String mainPage() {
        return "form/mainPage";
    }

    @GetMapping("/write")
    public String writePage(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "form/writePage";
    }
}
