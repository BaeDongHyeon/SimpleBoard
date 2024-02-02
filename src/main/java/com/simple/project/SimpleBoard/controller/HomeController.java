package com.simple.project.SimpleBoard.controller;

import com.simple.project.SimpleBoard.domain.form.MemberForm;
import com.simple.project.SimpleBoard.service.MemberService;
import com.simple.project.SimpleBoard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/")
    public String mainPage(Model model, @CookieValue(name = "memberId", required = false) Long memberId) {
        model.addAttribute("posts", postService.findAllPosts());
        model.addAttribute("memberId", memberId);

        if (memberId != null) {
            MemberForm memberForm = memberService.findMember(memberId);
            model.addAttribute("memberName", memberForm.getName());
        }

        return "form/mainPage";
    }
}
