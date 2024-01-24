package com.simple.project.SimpleBoard.repository;

import com.simple.project.SimpleBoard.domain.Post;
import com.simple.project.SimpleBoard.domain.form.PostForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("게시글이 저장되어야 한다.")
    void savePost() {
        // given
        Post post = Post.createPostBuilder()
                .title("제목")
                .writer("홍길동")
                .content("내용")
                .build();

        // when
        Post resultPost = postRepository.save(post);

        // then
        assertThat(resultPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(resultPost.getWriter()).isEqualTo(post.getWriter());
        assertThat(resultPost.getContent()).isEqualTo(post.getContent());
    }

    @Test
    @DisplayName("게시글이 수정되어야 한다.")
    void updatePost() {
        // given
        Post post = Post.createPostBuilder()
                .title("제목")
                .writer("홍길동")
                .content("내용")
                .build();

        Long postId = postRepository.save(post).getId();

        Post findPost = postRepository.findById(postId).get();

        // when
        PostForm postForm = PostForm.builder()
                .id(postId)
                .title("수정된 제목")
                .writer("수정된 이름")
                .content("수정된 내용")
                .build();

        findPost.update(postForm.getTitle(), postForm.getWriter(), postForm.getContent());
        Post resultPost = postRepository.save(findPost);

        // then
        assertThat(resultPost.getTitle()).isEqualTo(postForm.getTitle());
        assertThat(resultPost.getWriter()).isEqualTo(postForm.getWriter());
        assertThat(resultPost.getContent()).isEqualTo(postForm.getContent());
    }

    @Test
    @DisplayName("게시글이 삭제되어야 한다.")
    void deletePost() {
        // given
        Post post = Post.createPostBuilder()
                .title("제목")
                .writer("홍길동")
                .content("내용")
                .build();

        Long postId = postRepository.save(post).getId();

        // when
        postRepository.deleteById(postId);
        List<Post> findAll = postRepository.findAll();

        // then
        assertThat(findAll.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("모든 게시글이 조회되어야 한다.")
    void findAllPosts() {
        // given
        Post post1 = Post.createPostBuilder()
                .title("제목")
                .writer("홍길동")
                .content("내용")
                .build();
        postRepository.save(post1);

        Post post2 = Post.createPostBuilder()
                .title("제목")
                .writer("홍길동")
                .content("내용")
                .build();
        postRepository.save(post2);

        Post post3 = Post.createPostBuilder()
                .title("제목")
                .writer("홍길동")
                .content("내용")
                .build();
        postRepository.save(post3);

        // when
        List<Post> findAll = postRepository.findAll();
        assertThat(findAll.size()).isEqualTo(3);
    }
}