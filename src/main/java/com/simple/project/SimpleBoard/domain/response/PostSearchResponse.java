package com.simple.project.SimpleBoard.domain.response;

import com.simple.project.SimpleBoard.domain.Post;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class PostSearchResponse {

    private Long id;
    private String title;
    private String writer;
    private String content;

    @Builder
    public PostSearchResponse(Long id, String title, String writer, String content) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public Post toEntity() {
        return Post.updatePostBuilder()
                .id(id)
                .title(title)
                .writer(writer)
                .content(content)
                .build();
    }
}
