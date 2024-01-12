package com.simple.project.SimpleBoard.controller;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.domain.Post;
import com.simple.project.SimpleBoard.service.MemberService;
import com.simple.project.SimpleBoard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/")
    public String mainPage(Model model) {
        List<Post> posts = postService.findAllPosts();
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
        model.addAttribute("post", new Post());
        return "form/writePage";
    }

    @GetMapping("/post/detail/{postId}")
    public String postDetailPage(@PathVariable("postId") Long postId, Model model) {
        Optional<Post> findPost = postService.findPost(postId);
        Post post = findPost.get();
        model.addAttribute("post", post);
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
    public String write(Post post) {
        postService.writePost(post);
        return "redirect:/";
    }
}
