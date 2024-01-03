package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    
    @BeforeEach
    void before() {
        Member member = new Member();
        member.setEmail("a@naver.com");
        member.setPassword("1111");
        member.setName("가가가");
        
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setEmail("b@naver.com");
        member2.setPassword("2222");
        member2.setName("나나나");

        memberRepository.save(member2);

        Member member3 = new Member();
        member3.setEmail("c@naver.com");
        member3.setPassword("3333");
        member3.setName("다다다");
        
        memberRepository.save(member3);
    }
    
    @Test
    @DisplayName("회원이 저장되어야 한다.")
    void signupMember() {
        //given
        Member member = new Member();
        member.setEmail("new@naver.com");
        member.setPassword("4444");
        member.setName("라라라");

        // when
        memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findById(4L);

        // then
        assertThat(findMember.get().getEmail()).isEqualTo(member.getEmail());
        assertThat(findMember.get().getPassword()).isEqualTo(member.getPassword());
        assertThat(findMember.get().getName()).isEqualTo(member.getName());

    }

    @Test
    @DisplayName("회원이 삭제되어야 한다.")
    void deleteMember() {
        // given
        Member member2 = new Member();
        member2.setId(2L);
        member2.setEmail("b@naver.com");
        member2.setPassword("2222");
        member2.setName("나나나");

        // when
        memberRepository.deleteById(member2.getId());
        Optional<Member> findMember = memberRepository.findById(2L);

        // then
        assertThat(findMember).isEqualTo(Optional.empty());
    }
}