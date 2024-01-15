package com.simple.project.SimpleBoard.domain.dto;

import com.simple.project.SimpleBoard.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostCallResponse {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime updatedDate;

    @Builder
    public PostCallResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
        this.updatedDate = post.getUpdatedDate();
    }
}
