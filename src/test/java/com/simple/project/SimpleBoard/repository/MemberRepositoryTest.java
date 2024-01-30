package com.simple.project.SimpleBoard.repository;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.domain.form.MemberForm;
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

    @Test
    @DisplayName("회원 번호로 회원이 조회되어야 한다.")
    void findMember_id() {
        // given
        Member member1 = Member.createMember()
                .email("1@1.com")
                .password("1111")
                .name("홍길동1")
                .build();

        Member member2 = Member.createMember()
                .email("2@2.com")
                .password("2222")
                .name("홍길동2")
                .build();

        Long memberId = memberRepository.save(member1).getId();
        memberRepository.save(member2);

        // when
        Member findMember = memberRepository.findById(memberId).get();

        // then
        assertThat(findMember.getEmail()).isEqualTo(member1.getEmail());
        assertThat(findMember.getPassword()).isEqualTo(member1.getPassword());
        assertThat(findMember.getName()).isEqualTo(member1.getName());
    }
}