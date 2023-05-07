package com.yukeon.board.domain.board.dto.response;


import com.yukeon.board.domain.board.entitiy.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostInfoDto {
    private String title;
    private String content;
    private List<RelatedPostInfoDto> relatedPostList;
    private LocalDateTime createdAt;

    public static PostInfoDto from(Post post) {
        return new PostInfoDto(
                post.getContent(),
                post.getContent(),
                post.getRelatedPosts()
                        .stream()
                        .map(RelatedPostInfoDto::from)
                        .collect(Collectors.toList()),
                post.getCreatedAt()
        );
    }

}
