package com.simple.project.SimpleBoard.domain.response;

import com.simple.project.SimpleBoard.domain.Post;
import com.simple.project.SimpleBoard.domain.form.PostForm;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostSearchResponse {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime lastModifiedDate;

    @Builder
    public PostSearchResponse(Long id, String title, String writer, String content, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Post toEntity() {
        return Post.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .content(content)
                .build();
    }

    public PostForm toForm() {
        return PostForm.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .content(content)
                .build();
    }
}
