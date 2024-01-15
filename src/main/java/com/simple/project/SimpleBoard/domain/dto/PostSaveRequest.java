package com.simple.project.SimpleBoard.domain.dto;

import com.simple.project.SimpleBoard.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequest {
    private String title;
    private String detail;
    private String writer;

    @Builder
    public PostSaveRequest(String title, String detail, String writer) {
        this.title = title;
        this.detail = detail;
        this.writer = writer;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .detail(detail)
                .writer(writer)
                .build();
    }
}
