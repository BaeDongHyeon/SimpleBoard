package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.domain.dto.MemberSaveRequest;
import com.simple.project.SimpleBoard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signupMember(MemberSaveRequest memberSaveRequest) {
        memberRepository.save(memberSaveRequest.toEntity());
    }

    public void deleteMember(Member member) {
        memberRepository.deleteById(member.getId());
    }
}
