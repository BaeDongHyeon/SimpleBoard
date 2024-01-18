package com.simple.project.SimpleBoard.repository;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.domain.dto.MemberSaveRequest;
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

}