package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Post;
import com.simple.project.SimpleBoard.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
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

}