package com.study.mall.item.entity;

import com.study.mall.item.dto.etc.ItemUpdateDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "price")
    private Integer price;

    @CreatedDate
    @Column(name = "created_dt")
    private LocalDateTime createdDt;

    @LastModifiedDate
    @Column(name = "updated_dt")
    private LocalDateTime updatedDt;

    @Builder
    public ItemEntity(String itemName, String itemDescription, Integer price) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
    }

    /**
     * 상품 수정
     * @param itemUpdateDto 상품 수정 데이터 Dto
     */
    public void update(ItemUpdateDto itemUpdateDto) {
        this.itemName = itemUpdateDto.getItemName();
        this.itemDescription = itemUpdateDto.getItemDescription();
        this.price = itemUpdateDto.getPrice();
    }
}
