package com.simple.project.SimpleBoard.controller;

import com.simple.project.SimpleBoard.domain.form.MemberForm;
import com.simple.project.SimpleBoard.domain.request.MemberLoginRequest;
import com.simple.project.SimpleBoard.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
        model.addAttribute("memberLoginRequest", new MemberLoginRequest());
        return "form/loginPage";
    }

    @PostMapping("/login")
    public String login(@Valid MemberLoginRequest memberLoginRequest, BindingResult bindingResult, HttpServletResponse response) {
        MemberForm loginMember = memberService.loginMember(memberLoginRequest);
        if (loginMember.getId() == null || bindingResult.hasErrors()) {
            return "form/loginPage";
        }
        Cookie cookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(cookie);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
