package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Post;
import com.simple.project.SimpleBoard.domain.dto.PostCallResponse;
import com.simple.project.SimpleBoard.domain.dto.PostSaveRequest;
import com.simple.project.SimpleBoard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void writePost(PostSaveRequest postSaveRequest) {
        postRepository.save(postSaveRequest.toEntity());
    }

    public List<PostCallResponse> findAllPosts() {
        List<Post> allPosts = postRepository.findAll();
        return allPosts.stream()
                .map(post -> PostCallResponse.builder()
                        .post(post)
                        .build())
                .collect(Collectors.toList());
    }

    public Optional<Post> findPost(Long postId) {
        return postRepository.findById(postId);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
