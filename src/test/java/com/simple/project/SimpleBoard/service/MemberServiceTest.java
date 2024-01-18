package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.domain.dto.MemberSaveRequest;
import com.simple.project.SimpleBoard.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    
    @Test
    @DisplayName("회원이 저장되어야 한다.")
    void signupMember() {
        //given
        MemberSaveRequest memberSaveRequest = MemberSaveRequest.builder()
                        .email("new@naver.com")
                        .password("1111")
                        .name("홍길동")
                        .build();

        // when
        Long memberId = memberRepository.save(memberSaveRequest.toEntity()).getId();
        Optional<Member> findMember = memberRepository.findById(memberId);

        // then
        assertThat(findMember.get().getEmail()).isEqualTo(memberSaveRequest.getEmail());
        assertThat(findMember.get().getPassword()).isEqualTo(memberSaveRequest.getPassword());
        assertThat(findMember.get().getName()).isEqualTo(memberSaveRequest.getName());

    }

    @Test
    @DisplayName("회원이 삭제되어야 한다.")
    void deleteMember() {
        // given
        MemberSaveRequest memberSaveRequest1 = MemberSaveRequest.builder()
                .email("new1@naver.com")
                .password("1111")
                .name("홍길동1")
                .build();
        Long memberId = memberRepository.save(memberSaveRequest1.toEntity()).getId();

        MemberSaveRequest memberSaveRequest2 = MemberSaveRequest.builder()
                .email("new2@naver.com")
                .password("2222")
                .name("홍길동2")
                .build();
        memberRepository.save(memberSaveRequest2.toEntity());

        // when
        memberRepository.deleteById(memberId);
        Optional<Member> findMember = memberRepository.findById(memberId);

        // then
        assertThat(findMember).isEqualTo(Optional.empty());
    }
}