package com.yukeon.board.domain.board.repository;

import com.yukeon.board.domain.board.entitiy.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByContentContaining(String word);
}
