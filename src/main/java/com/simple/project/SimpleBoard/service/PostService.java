package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Post;
import com.simple.project.SimpleBoard.domain.form.PostForm;
import com.simple.project.SimpleBoard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void savePost(PostForm postForm) {
        Post post = Post.builder()
                .title(postForm.getTitle())
                .writer(postForm.getWriter())
                .content(postForm.getContent())
                .build();
        postRepository.save(post);
    }
}
