package com.yukeon.board.domain.board.controller;

import com.yukeon.board.domain.board.dto.request.PostCreateRequestDto;
import com.yukeon.board.domain.board.dto.response.PostInfoDto;
import com.yukeon.board.domain.board.dto.response.PostListDto;
import com.yukeon.board.domain.board.service.PostService;
import com.yukeon.board.global.result.ResultCode;
import com.yukeon.board.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(summary = "findPostList", description = "게시글 목록 조회")
    @GetMapping
    public ResponseEntity<ResultResponse> getAllPostList() {
        List<PostListDto> postList = postService.getPostList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResultResponse.of(ResultCode.POST_GET_SUCCESS, postList));
    }

    @Operation(summary = "findPost", description = "게시글 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getPost(Long id) {
        PostInfoDto postInfo = postService.getPost(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResultResponse.of(ResultCode.POST_GET_SUCCESS, postInfo));
    }

    @Operation(summary = "savePost", description = "게시글 저장")
    @PostMapping
    public ResponseEntity<ResultResponse> savePost(PostCreateRequestDto dto ) {
        postService.savePost(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResultResponse.of(ResultCode.POST_CREATE_SUCCESS));
    }
}
