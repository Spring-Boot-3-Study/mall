package com.study.mall.common.exception;

import com.study.mall.common.enums.Response;

public interface ErrorException {

    Response getResponse();

    Object getResult();
}
