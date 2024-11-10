package com.study.mall.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PageResponseDto<T> {

    private T content;

    private Long totalCount;

    private Integer pageNo;

    private Integer pageSize;

    private Integer totalPages;

    private Boolean isLastPage;
}
