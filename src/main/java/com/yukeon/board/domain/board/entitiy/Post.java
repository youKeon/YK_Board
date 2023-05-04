package com.yukeon.board.domain.board.entitiy;

import com.yukeon.board.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

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
}
