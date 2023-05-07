package com.yukeon.board.domain.board.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostCreateRequestDto {
    private String title;
    private String content;
}
