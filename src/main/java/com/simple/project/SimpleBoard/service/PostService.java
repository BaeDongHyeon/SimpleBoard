package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Post;
import com.simple.project.SimpleBoard.domain.form.PostForm;
import com.simple.project.SimpleBoard.domain.response.PostSearchResponse;
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

    public List<PostSearchResponse> findAllPosts() {
        List<Post> allPosts = postRepository.findAll();
        return allPosts.stream()
                .map(post -> PostSearchResponse.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .writer(post.getWriter())
                        .content(post.getContent())
                        .build())
                .collect(Collectors.toList());
    }

    public Long savePost(PostForm postForm) {
        Post post = Post.createPostBuilder()
                .title(postForm.getTitle())
                .writer(postForm.getWriter())
                .content(postForm.getContent())
                .build();
        return postRepository.save(post).getId();
    }

    public PostSearchResponse findPost(Long postId) {
        Optional<Post> result = postRepository.findById(postId);
        Post post = result.get();

        return PostSearchResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .writer(post.getWriter())
                .content(post.getContent())
                .build();
    }

    public void updatePost(PostForm postForm) {
        Post post = postRepository.findById(postForm.getId()).get();
        post.update(postForm.getTitle(), postForm.getWriter(), postForm.getContent());
        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
