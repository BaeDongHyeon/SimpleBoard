package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.domain.form.MemberForm;
import com.simple.project.SimpleBoard.domain.request.MemberLoginRequest;
import com.simple.project.SimpleBoard.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    @Transactional
    @DisplayName("회원이 저장되어야 한다.")
    void saveMember() {
        // given
        MemberForm memberForm = MemberForm.builder()
                .email("abc@abc.com")
                .password("1234")
                .name("홍길동1")
                .build();

        // when
        Long memberId = memberService.saveMember(memberForm);

        // then
        assertThat(memberId).isEqualTo(1L);
    }

    @Test
    @Transactional
    @DisplayName("회원이 로그인이 성공되면 번호와 이름이 반환되어야 한다.")
    void loginMember() {
        // given
        MemberForm memberForm = MemberForm.builder()
                .email("abc@abc.com")
                .password("1234")
                .name("홍길동1")
                .build();

        Long memberId = memberService.saveMember(memberForm);

        MemberLoginRequest memberLoginRequest = MemberLoginRequest.builder()
                .email("abc@abc.com")
                .password("1234")
                .build();

        // when
        MemberForm loginMember = memberService.loginMember(memberLoginRequest);

        // then
        assertThat(loginMember.getId()).isEqualTo(memberId);
        assertThat(loginMember.getName()).isEqualTo(memberForm.getName());
    }

    @Test
    void findMember() {
    }
}