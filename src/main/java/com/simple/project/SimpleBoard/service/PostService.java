package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Post;
import com.simple.project.SimpleBoard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void writePost(Post post) {
        postRepository.save(post);
    }
}
