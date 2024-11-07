package com.study.mall.item.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ItemVo {

    private final String itemName;

    private final String itemDescription;

    private final Integer price;

    private final LocalDateTime createdDt;

    private final LocalDateTime updatedDt;
}
