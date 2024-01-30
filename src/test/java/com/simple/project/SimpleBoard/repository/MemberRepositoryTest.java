package com.simple.project.SimpleBoard.repository;

import com.simple.project.SimpleBoard.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원이 저장되어야 한다.")
    void saveMember() {
        // given
        Member member = Member.createMember()
                .email("abc@abc.com")
                .password("1234")
                .name("홍길동")
                .build();

        // when
        Member saveMember = memberRepository.save(member);

        // then
        assertThat(saveMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(saveMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(saveMember.getName()).isEqualTo(member.getName());
    }


}