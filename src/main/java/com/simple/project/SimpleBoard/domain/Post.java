package com.simple.project.SimpleBoard.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String writer;

    private String content;

    @Builder
    public Post(Long id, String title, String writer, String content) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    @Builder(builderMethodName = "createPostBuilder")
    public Post(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public void update(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
