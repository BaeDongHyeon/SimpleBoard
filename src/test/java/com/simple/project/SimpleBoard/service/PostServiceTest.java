package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Post;
import com.simple.project.SimpleBoard.domain.form.PostForm;
import com.simple.project.SimpleBoard.domain.response.PostSearchResponse;
import com.simple.project.SimpleBoard.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    @DisplayName("전체 게시글이 조회되어야 한다.")
    @Transactional
    void findAllPosts() {
        // given
        List<Post> posts = new ArrayList<>();
        Post post1 = Post.builder()
                .id(1L)
                .title("제목1")
                .writer("작성자1")
                .content("내용1")
                .build();

        Post post2 = Post.builder()
                .id(2L)
                .title("제목2")
                .writer("작성자2")
                .content("내용2")
                .build();

        Post post3 = Post.builder()
                .id(3L)
                .title("제목3")
                .writer("작성자3")
                .content("내용3")
                .build();

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);

        // stub
        when(postRepository.findAll()).thenReturn(posts);

        // when
        List<PostSearchResponse> findAllPosts = postService.findAllPosts();

        // then
        assertThat(findAllPosts.size()).isEqualTo(3);

    }

    @Test
    @DisplayName("게시글이 저장되어야 한다.")
    @Transactional
    void savePost() {
        // given
        Post post1 = Post.builder()
                .id(1L)
                .title("제목1")
                .writer("작성자1")
                .content("내용1")
                .build();

        // stub
        when(postRepository.save(any())).thenReturn(post1);

        // when
        PostForm postForm = PostForm.builder()
                        .title(post1.getTitle())
                        .writer(post1.getWriter())
                        .content(post1.getContent())
                        .build();

        Long postId = postService.savePost(postForm);

        // then
        assertThat(postId).isEqualTo(1L);
    }

    @Test
    @DisplayName("특정 게시글이 조회되어야 한다.")
    void findPost() {
        // given
        Post post1 = Post.builder()
                .id(1L)
                .title("제목1")
                .writer("작성자1")
                .content("내용1")
                .build();

        // stub
        when(postRepository.findById(post1.getId())).thenReturn(Optional.of(post1));

        // when
        PostSearchResponse postSearchResponse = postService.findPost(post1.getId());

        // then
        assertThat(postSearchResponse.getTitle()).isEqualTo(post1.getTitle());
    }

    @Test
    void updatePost() {
        // given
        Post post1 = Post.builder()
                .id(1L)
                .title("제목1")
                .writer("작성자1")
                .content("내용1")
                .build();

        // stub
        when(postRepository.save(any())).thenReturn(post1);
        when(postRepository.findById(post1.getId())).thenReturn(Optional.of(post1));

        // when
        PostForm postForm = PostForm.builder()
                .title(post1.getTitle())
                .writer(post1.getWriter())
                .content(post1.getContent())
                .build();
        Long postId = postService.savePost(postForm);

        postService.updatePost(PostForm.builder()
                .id(postId)
                .title("수정")
                .writer("수정")
                .content("수정")
                .build());

        PostSearchResponse postSearchResponse = postService.findPost(post1.getId());

        // then
        assertThat(postSearchResponse.getTitle()).isEqualTo("수정");
    }

    @Test
    void deletePost() {
        // given
        Post post1 = Post.builder()
                .id(1L)
                .title("제목1")
                .writer("작성자1")
                .content("내용1")
                .build();

        // stub
        when(postRepository.save(any())).thenReturn(post1);

        // when
        PostForm postForm = PostForm.builder()
                .title(post1.getTitle())
                .writer(post1.getWriter())
                .content(post1.getContent())
                .build();
        Long postId = postService.savePost(postForm);

        Long deleteId = postService.deletePost(postId);

        // then
        assertThat(deleteId).isEqualTo(postId);
    }
}