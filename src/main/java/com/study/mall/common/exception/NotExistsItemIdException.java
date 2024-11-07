package com.study.mall.common.exception;

import com.study.mall.common.enums.ItemResponse;
import com.study.mall.common.enums.Response;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;

@Getter
public class NotExistsItemIdException extends RuntimeException implements ErrorException {

    private final Response response;

    private final Map<String, Long> result;

    public NotExistsItemIdException(Long itemId) {
        this.response = ItemResponse.ITEM_NOT_EXISTS_ITEM_ID;
        this.result = Collections.singletonMap("itemId", itemId);
    }
}
