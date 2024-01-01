package com.simple.project.SimpleBoard.repository;

import com.simple.project.SimpleBoard.domain.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원이 저장되어야 한다.")
    void saveMember() {
        // given
        Member member = new Member();
        member.setEmail("abc@naver.com");
        member.setPassword("1234");
        member.setName("가나다");

        // when
        memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findById(1L);

        // then
        assertThat(findMember.get().getEmail()).isEqualTo(member.getEmail());
        assertThat(findMember.get().getPassword()).isEqualTo(member.getPassword());
        assertThat(findMember.get().getName()).isEqualTo(member.getName());
    }

}