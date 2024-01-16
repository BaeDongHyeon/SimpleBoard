package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Post;
import com.simple.project.SimpleBoard.domain.dto.PostSaveRequest;
import com.simple.project.SimpleBoard.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("게시글이 작성되어야 한다.")
    void writePost() {
        // given
        PostSaveRequest postSaveRequest = new PostSaveRequest(null, "제목1", "내용1", "작성자1");
        Post post = postSaveRequest.toEntity();

        // when
        Long postId = postRepository.save(post).getId();
        Optional<Post> findPost = postRepository.findById(postId);

        // then
        Post onePost = findPost.get();
        assertThat(onePost.getTitle()).isEqualTo(postSaveRequest.getTitle());
        assertThat(onePost.getContent()).isEqualTo(postSaveRequest.getContent());
        assertThat(onePost.getWriter()).isEqualTo(postSaveRequest.getWriter());
    }

    @Test
    @DisplayName("모든 게시글이 조회되어야 한다.")
    void findAllPosts() {
        // given
        PostSaveRequest postSaveRequest1 = new PostSaveRequest(null, "제목1", "내용1", "작성자1");
        postRepository.save(postSaveRequest1.toEntity());

        PostSaveRequest postSaveRequest2 = new PostSaveRequest(null, "제목2", "내용2", "작성자2");
        postRepository.save(postSaveRequest2.toEntity());

        PostSaveRequest postSaveRequest3 = new PostSaveRequest(null, "제목3", "내용3", "작성자3");
        postRepository.save(postSaveRequest3.toEntity());

        // when
        List<Post> findAll = postRepository.findAll();
        int count = findAll.size();

        // then
        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("해당 게시글이 검색되어야 한다.")
    void findPost() {
        // given
        PostSaveRequest postSaveRequest1 = new PostSaveRequest(null, "제목1", "내용1", "작성자1");
        postRepository.save(postSaveRequest1.toEntity());

        PostSaveRequest postSaveRequest2 = new PostSaveRequest(null, "제목2", "내용2", "작성자2");
        Long postId = postRepository.save(postSaveRequest2.toEntity()).getId();

        PostSaveRequest postSaveRequest3 = new PostSaveRequest(null, "제목3", "내용3", "작성자3");
        postRepository.save(postSaveRequest3.toEntity());

        // when
        Optional<Post> findPost = postRepository.findById(postId);
        Post post = findPost.get();

        // then
        assertThat(post.getTitle()).isEqualTo(postSaveRequest2.getTitle());
        assertThat(post.getContent()).isEqualTo(postSaveRequest2.getContent());
        assertThat(post.getWriter()).isEqualTo(postSaveRequest2.getWriter());
    }
}