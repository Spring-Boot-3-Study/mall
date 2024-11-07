package com.study.mall.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDto<T> {

    private Integer code;

    private String message;

    private T result;

    @Builder
    public ResponseDto(Integer code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }
}
