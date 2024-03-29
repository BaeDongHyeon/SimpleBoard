package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.domain.form.MemberForm;
import com.simple.project.SimpleBoard.domain.request.MemberLoginRequest;
import com.simple.project.SimpleBoard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long saveMember(MemberForm memberForm) {
        Member member = Member.createMember()
                        .email(memberForm.getEmail())
                        .password(memberForm.getPassword())
                        .name(memberForm.getName())
                        .build();

        return memberRepository.save(member).getId();
    }

    public MemberForm loginMember(MemberLoginRequest memberLoginRequest) {
        Optional<Member> findEmail = memberRepository.findByEmail(memberLoginRequest.getEmail());

        if (findEmail.isEmpty()) {
            return MemberForm.builder()
                    .id(null)
                    .build();
        }

        Member member = findEmail.get();
        if (member.login(memberLoginRequest.getPassword())) {
            return MemberForm.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .build();
        }
        return MemberForm.builder()
                .id(null)
                .build();
    }

    public MemberForm findMember(Long memberId) {
        Optional<Member> result = memberRepository.findById(memberId);

        if (result.isEmpty()) {
            return new MemberForm();
        }

        Member member = result.get();
        return MemberForm.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
    }
}
