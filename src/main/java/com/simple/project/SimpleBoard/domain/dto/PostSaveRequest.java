package com.simple.project.SimpleBoard.domain.dto;

import com.simple.project.SimpleBoard.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PostSaveRequest {
    private Long id;
    private String title;
    private String content;
    private String writer;

    @Builder
    public PostSaveRequest(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Post toEntity() {
        return Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
