package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Post;
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

    @BeforeEach
    void setup() {
        Post post2 = new Post();
        post2.setId(2L);
        post2.setTitle("제목2");
        post2.setDetail("내용2");
        post2.setWriter("abc2");

        Post post3 = new Post();
        post3.setId(3L);
        post3.setTitle("제목3");
        post3.setDetail("내용3");
        post3.setWriter("abc3");

        postRepository.save(post2);
        postRepository.save(post3);
    }

    @Test
    @DisplayName("게시글이 작성되어야 한다.")
    void writePost() {
        // given
        Post post = new Post();
        post.setId(1L);
        post.setTitle("제목");
        post.setWriter("abc");

        LocalDateTime localDateTime = LocalDateTime.now();
        post.setCreatedDate(localDateTime);

        // when
        postRepository.save(post);
        Optional<Post> findPost = postRepository.findById(1L);

        // then
        assertThat(findPost.get().getTitle()).isEqualTo(post.getTitle());
        assertThat(findPost.get().getWriter()).isEqualTo(post.getWriter());
        assertThat(findPost.get().getCreatedDate()).isEqualTo(localDateTime);
    }

    @Test
    @DisplayName("모든 게시글이 조회되어야 한다.")
    void findAllPosts() {
        List<Post> findAll = postRepository.findAll();

        Post post2 = findAll.get(0);
        Post post3 = findAll.get(1);

        assertThat(post2.getTitle()).isEqualTo("제목2");
        assertThat(post2.getDetail()).isEqualTo("내용2");
        assertThat(post2.getWriter()).isEqualTo("abc2");

        assertThat(post3.getTitle()).isEqualTo("제목3");
        assertThat(post3.getDetail()).isEqualTo("내용3");
        assertThat(post3.getWriter()).isEqualTo("abc3");
    }
}