package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.domain.form.MemberForm;
import com.simple.project.SimpleBoard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
