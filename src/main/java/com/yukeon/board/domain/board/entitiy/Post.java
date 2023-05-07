package com.yukeon.board.domain.board.entitiy;

import com.yukeon.board.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<RelatedPost> relatedPosts = new ArrayList<>();

    @Column(nullable = false)
    private boolean isDeleted;

    @Builder
    public Post(Long id,
                String title,
                String content) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.isDeleted = false;
    }

    public void addRelatedPost(List<RelatedPost> relatedPost) {
        this.relatedPosts = relatedPost;
    }
}
