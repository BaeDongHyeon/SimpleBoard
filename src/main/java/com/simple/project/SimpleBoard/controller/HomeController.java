package com.simple.project.SimpleBoard.controller;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.domain.dto.PostCallResponse;
import com.simple.project.SimpleBoard.domain.dto.PostSaveRequest;
import com.simple.project.SimpleBoard.service.MemberService;
import com.simple.project.SimpleBoard.service.PostService;
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
    public String mainPage(Model model) {
        List<PostCallResponse> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
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
    public String signup(Member member) {
        memberService.signupMember(member);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(Member member) {
        return "redirect:/";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute("postSaveRequest") PostSaveRequest postSaveRequest) {
        postService.writePost(postSaveRequest);
        return "redirect:/";
    }
}
