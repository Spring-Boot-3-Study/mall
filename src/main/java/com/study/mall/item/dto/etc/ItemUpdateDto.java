package com.study.mall.item.dto.etc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ItemUpdateDto {

    private String itemName;

    private String itemDescription;

    private Integer price;
}
