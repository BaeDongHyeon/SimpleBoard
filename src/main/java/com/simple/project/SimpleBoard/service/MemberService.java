package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.domain.form.MemberForm;
import com.simple.project.SimpleBoard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(MemberForm memberForm) {
        Member member = Member.createMember()
                        .email(memberForm.getEmail())
                        .password(memberForm.getPassword())
                        .name(memberForm.getName())
                        .build();

        memberRepository.save(member);
    }

    public MemberForm loginMember(MemberForm memberForm) {
        Optional<Member> findEmail = memberRepository.findByEmail(memberForm.getEmail());

        if (findEmail.isEmpty()) {
            return MemberForm.builder()
                    .id(null)
                    .build();
        }

        Member member = findEmail.get();

        if (member.login(memberForm.getPassword())) {
            return MemberForm.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .build();
        }
        return MemberForm.builder()
                .id(null)
                .build();
    }
}
