package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.domain.form.MemberForm;
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
    void loginMember() {
    }

    @Test
    void findMember() {
    }
}