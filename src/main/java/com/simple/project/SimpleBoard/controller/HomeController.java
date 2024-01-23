package com.simple.project.SimpleBoard.controller;

import com.simple.project.SimpleBoard.domain.form.PostForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/write")
    public String write(@Valid PostForm postForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form/writePage";
        }
        return "redirect:/";
    }
}
