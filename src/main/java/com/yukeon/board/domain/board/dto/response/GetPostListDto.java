package com.yukeon.board.domain.board.dto.response;

import com.yukeon.board.domain.board.entitiy.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class GetPostListDto {
    private String content;
    private LocalDateTime createdAt;

    public static GetPostListDto from(Post post) {
        return new GetPostListDto(
                post.getContent(),
                post.getCreatedAt()
        );
    }
}
