package com.simple.project.SimpleBoard.controller;

import com.simple.project.SimpleBoard.domain.form.MemberForm;
import com.simple.project.SimpleBoard.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "form/signupPage";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberForm memberForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form/signupPage";
        }

        memberService.saveMember(memberForm);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "form/loginPage";
    }

    @PostMapping("/login")
    public String login(@Valid MemberForm memberForm, BindingResult bindingResult) {
        MemberForm loginMember = memberService.loginMember(memberForm);
        if (loginMember == null || bindingResult.hasErrors()) {
            return "form/loginPage";
        }
        return "redirect:/";
    }
}
