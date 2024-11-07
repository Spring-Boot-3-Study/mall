package com.study.mall.common.enums;

import com.study.mall.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Response Enum 클래스 추상화
 */
public interface Response {

    Integer getCode();

    String getMessage();

    HttpStatus getStatus();

    ResponseDto<Map<String, Object>> toResponseDto();

    <T> ResponseDto<T> toResponseDto(T result);
}
