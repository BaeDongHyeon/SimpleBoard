package com.simple.project.SimpleBoard.domain.dto;

import com.simple.project.SimpleBoard.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemberSaveRequest {
    private String email;
    private String password;
    private String name;

    @Builder
    public MemberSaveRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
