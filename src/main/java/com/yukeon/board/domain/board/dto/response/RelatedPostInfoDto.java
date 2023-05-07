package com.yukeon.board.domain.board.dto.response;

import com.yukeon.board.domain.board.entitiy.RelatedPost;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RelatedPostInfoDto {
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public static RelatedPostInfoDto from(RelatedPost relatedPost) {
        return new RelatedPostInfoDto(
                relatedPost.getTitle(),
                relatedPost.getContent(),
                relatedPost.getCreatedAt()
        );
    }

}
