package com.yukeon.board.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** {행위}_{목적어}_{성공여부} message 는 동사 명사형으로 마무리 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    // project
    POST_CREATE_SUCCESS("P001", "프로젝트 생성 성공"),
    POST_GET_SUCCESS("P002", "프로젝트 조회 성공"),
    POST_DELETE_SUCCESS("P003", "프로젝트 삭제 성공"),
    POST_UPDATE_SUCCESS("P004", "프로젝트 수정 성공");

    private final String code;
    private final String message;
}