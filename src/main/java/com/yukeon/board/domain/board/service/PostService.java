package com.yukeon.board.domain.board.service;

import com.yukeon.board.domain.board.dto.response.GetPostListDto;
import com.yukeon.board.domain.board.entitiy.Post;
import com.yukeon.board.domain.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<GetPostListDto> getPost() {
        return postRepository.findAll()
                .stream()
                .map(GetPostListDto::from)
                .collect(Collectors.toList());


    }

    public void setTestPost(int count) {
        for (int i = 0; i < count; i++) {
            postRepository.save(new Post((long) i, "content" + i, "title" + i));
        }

    }
}
