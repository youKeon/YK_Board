package com.yukeon.board.domain.board.controller;

import com.yukeon.board.domain.board.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PostController", description = "게시글 API")
@RestController
@RequestMapping("api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
}
