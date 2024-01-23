package com.simple.project.SimpleBoard.repository;

import com.simple.project.SimpleBoard.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
