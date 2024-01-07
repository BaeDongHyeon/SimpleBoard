package com.simple.project.SimpleBoard.controller;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String mainPage() {
        return "form/index";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("member", new Member());
        return "form/loginPage";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("member", new Member());
        return "form/signupPage";
    }

    @GetMapping("/write")
    public String writePage() {
        return "form/writePage";
    }

    @PostMapping("/member/new")
    public String signup(Member member) {
        memberService.signupMember(member);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(Member member) {
        return "redirect:/";
    }
}
