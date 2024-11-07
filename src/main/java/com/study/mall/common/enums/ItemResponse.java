package com.study.mall.common.enums;

import com.study.mall.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

public enum ItemResponse implements Response {

    ITEM_NOT_EXISTS_ITEM_ID(HttpStatus.BAD_REQUEST, 0, "등록되지 않은 상품 ID 입니다."),

    ITEM_CREATE_ITEM_SUCCESS(HttpStatus.OK,0, "상품 등록을 완료하였습니다."),
    ITEM_GET_ITEM_SUCCESS(HttpStatus.OK,0, "상품 조회를 완료하였습니다."),
    ITEM_UPDATE_ITEM_SUCCESS(HttpStatus.OK,0, "상품 수정을 완료하였습니다."),
    ITEM_DELETE_ITEM_SUCCESS(HttpStatus.OK,0, "상품 삭제을 완료하였습니다."),
    ;

    private final HttpStatus status;

    private final Integer code;

    private final String message;

    ItemResponse(HttpStatus status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public ResponseDto<Map<String, Object>> toResponseDto() {
        return ResponseDto.<Map<String, Object>>builder()
                .code(this.code)
                .message(this.message)
                .result(Collections.emptyMap())
                .build();
    }

    @Override
    public <T> ResponseDto<T> toResponseDto(T result) {
        return ResponseDto.<T>builder()
                .code(this.code)
                .message(this.message)
                .result(result)
                .build();
    }
}
