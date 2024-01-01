package com.simple.project.SimpleBoard.repository;

import com.simple.project.SimpleBoard.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
