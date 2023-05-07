package com.yukeon.board.domain.board.dto.response;

import com.yukeon.board.domain.board.entitiy.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostListDto {
    private String title;
    private LocalDateTime createdAt;

    public static PostListDto from(Post post) {
        return new PostListDto(
                post.getTitle(),
                post.getCreatedAt()
        );
    }
}
