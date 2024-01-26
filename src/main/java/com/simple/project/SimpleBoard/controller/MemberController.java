package com.simple.project.SimpleBoard.controller;

import com.simple.project.SimpleBoard.domain.form.MemberForm;
import com.simple.project.SimpleBoard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "form/signupPage";
    }
}
