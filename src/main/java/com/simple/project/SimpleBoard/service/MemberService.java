package com.simple.project.SimpleBoard.service;

import com.simple.project.SimpleBoard.domain.Member;
import com.simple.project.SimpleBoard.domain.dto.LoginRequest;
import com.simple.project.SimpleBoard.domain.dto.MemberSaveRequest;
import com.simple.project.SimpleBoard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long login(LoginRequest loginRequest) {
        Optional<Member> findEmailSameMember = memberRepository.findByEmail(loginRequest.getEmail());

        Member member = null;
        if (findEmailSameMember.isEmpty()) {
            return null;
        }
        member = findEmailSameMember.get();

        if (member.passwordMatch(loginRequest.getPassword())) {
            return member.getId();
        }
        return null;
    }

    public void signupMember(MemberSaveRequest memberSaveRequest) {
        memberRepository.save(memberSaveRequest.toEntity());
    }

    public void deleteMember(Member member) {
        memberRepository.deleteById(member.getId());
    }
}
