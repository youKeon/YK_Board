package com.yukeon.board.domain.board.controller;

import com.yukeon.board.domain.board.dto.response.GetPostListDto;
import com.yukeon.board.domain.board.service.PostService;
import com.yukeon.board.global.result.ResultCode;
import com.yukeon.board.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "PostController", description = "게시글 API")
@RestController
@RequestMapping("api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @Operation(summary = "findPost", description = "게시글 조회")
    @GetMapping
    public ResponseEntity<ResultResponse> getPost() {
        List<GetPostListDto> postList = postService.getPost();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResultResponse.of(ResultCode.POST_GET_SUCCESS, postList));
    }

    @Operation(summary = "TestData", description = "테스트 게시글 작성")
    @PostMapping("/test")
    public void setTestPost(int count) {
        postService.setTestPost(count);
    }
}
