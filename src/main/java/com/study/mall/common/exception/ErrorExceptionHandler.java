package com.study.mall.common.exception;

import com.study.mall.common.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler(NotExistsItemIdException.class)
    public ResponseEntity<ResponseDto<Map<String, Long>>> handleNotExistsItemIdException(NotExistsItemIdException notExistsItemIdException) {
        return ResponseEntity
                .status(notExistsItemIdException.getResponse().getStatus())
                .body(notExistsItemIdException.getResponse().toResponseDto(notExistsItemIdException.getResult()));
    }
}
