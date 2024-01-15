package com.simple.project.SimpleBoard.domain.dto;

import com.simple.project.SimpleBoard.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequest {
    private String title;
    private String content;
    private String writer;

    @Builder
    public PostSaveRequest(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
