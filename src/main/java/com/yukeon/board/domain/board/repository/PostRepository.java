package com.yukeon.board.domain.board.repository;

import com.yukeon.board.domain.board.entitiy.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
