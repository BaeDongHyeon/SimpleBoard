package com.simple.project.SimpleBoard.controller;

import com.simple.project.SimpleBoard.domain.form.PostForm;
import com.simple.project.SimpleBoard.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;



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
        if (postForm.getId() == null) {
            postService.savePost(postForm);
        } else {
            postService.updatePost(postForm);
        }

        return "redirect:/";
    }

    @GetMapping("/post/{postId}")
    public String postingPage(@PathVariable("postId") Long postId, Model model) {
        model.addAttribute("post", postService.findPost(postId));
        return "form/postingPage";
    }

    @GetMapping("/update/post/{postId}")
    public String updatePost(@PathVariable("postId") Long postId, Model model) {
        PostForm postForm = postService.findPost(postId).toForm();
        model.addAttribute("postForm", postForm);
        return "form/writePage";
    }

    @GetMapping("/delete/post/{postId}")
    public String postDelete(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return "redirect:/";
    }
}
