package com.simple.project.SimpleBoard.domain.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PostForm {

    private Long id;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "작성자를 입력해주세요.")
    private String writer;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Builder
    public PostForm(Long id, String title, String writer, String content) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
