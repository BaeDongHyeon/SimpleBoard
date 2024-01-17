package com.simple.project.SimpleBoard.controller;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.domain.dto.LoginRequest;
import com.simple.project.SimpleBoard.domain.dto.MemberSaveRequest;
import com.simple.project.SimpleBoard.domain.dto.PostCallResponse;
import com.simple.project.SimpleBoard.domain.dto.PostSaveRequest;
import com.simple.project.SimpleBoard.service.MemberService;
import com.simple.project.SimpleBoard.service.PostService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/")
    public String mainPage(Model model, @CookieValue(name = "memberId", required = false) Long memberId) {
        List<PostCallResponse> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("memberId", memberId);

        if (memberId != null) {
            Member member = memberService.findMember(memberId);
            model.addAttribute("memberName", member.getName());
        }
        return "form/index";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("member", new LoginRequest());
        return "form/loginPage";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("member", new MemberSaveRequest());
        return "form/signupPage";
    }

    @GetMapping("/write")
    public String writePage(Model model) {
        model.addAttribute("postSaveRequest", new PostSaveRequest());
        return "form/writePage";
    }

    @GetMapping("/update/post/{postId}")
    public String updatePost(@PathVariable("postId") Long postId, Model model) {
        PostCallResponse findPost = postService.findPost(postId);

        PostSaveRequest postSaveRequest = PostSaveRequest.builder()
                        .id(postId)
                        .title(findPost.getTitle())
                        .writer(findPost.getWriter())
                        .content(findPost.getContent())
                        .build();

        model.addAttribute("postSaveRequest", postSaveRequest);
        return "form/writePage";
    }

    @GetMapping("/delete/post/{postId}")
    public String deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return "redirect:/";
    }

    @GetMapping("/post/detail/{postId}")
    public String postDetailPage(@PathVariable("postId") Long postId, Model model) {
        PostCallResponse findPost = postService.findPost(postId);
        model.addAttribute("post", findPost);
        return "form/postDetailPage";
    }

    @PostMapping("/member/new")
    public String signup(MemberSaveRequest memberSaveRequest) {
        memberService.signupMember(memberSaveRequest);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(LoginRequest loginRequest, HttpServletResponse response) {
        Long memberId = memberService.login(loginRequest);

        if (memberId != null) {
            Cookie cookie = new Cookie("memberId", String.valueOf(memberId));
            response.addCookie(cookie);
        }

        return "redirect:/";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute("postSaveRequest") PostSaveRequest postSaveRequest) {
        postService.writePost(postSaveRequest);
        return "redirect:/";
    }
}
