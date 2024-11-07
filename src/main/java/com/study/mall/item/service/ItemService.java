package com.study.mall.item.service;

import com.study.mall.common.exception.NotExistsItemIdException;
import com.study.mall.item.dto.etc.ItemUpdateDto;
import com.study.mall.item.entity.ItemEntity;
import com.study.mall.item.repository.ItemRepository;
import com.study.mall.item.vo.ItemVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    /**
     * 상품 등록
     * @param itemName 상품명
     * @param itemDescription 상품 소개
     * @param price 상품 가격
     */
    @Transactional
    public void createItem(String itemName, String itemDescription, Integer price) {

        ItemEntity itemEntity = ItemEntity.builder()
                .itemName(itemName)
                .itemDescription(itemDescription)
                .price(price)
                .build();

        itemRepository.save(itemEntity);
    }

    /**
     * 상품 단건 조회
     * @param itemId 상품 ID
     * @return 상품 정보 Vo
     */
    @Transactional(readOnly = true)
    public ItemVo getItem(Long itemId) {

        ItemEntity itemEntity = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotExistsItemIdException(itemId));

        return ItemVo.builder()
                .itemName(itemEntity.getItemName())
                .itemDescription(itemEntity.getItemDescription())
                .price(itemEntity.getPrice())
                .createdDt(itemEntity.getCreatedDt())
                .updatedDt(itemEntity.getUpdatedDt())
                .build();
    }

    /**
     * 상품 수정
     * @param itemId 상품 ID
     * @param itemName 상품명
     * @param itemDescription 상품 소개
     * @param price 상품 가격
     */
    @Transactional
    public void updateItem(Long itemId, String itemName, String itemDescription, Integer price) {

        ItemEntity itemEntity = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotExistsItemIdException(itemId));

        ItemUpdateDto itemUpdateDto = ItemUpdateDto.builder()
                .itemName(itemName)
                .itemDescription(itemDescription)
                .price(price)
                .build();

        itemEntity.update(itemUpdateDto);
    }

    /**
     * 상품 삭제
     * @param itemId 상품 ID
     */
    @Transactional
    public void deleteItem(Long itemId) {

        ItemEntity itemEntity = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotExistsItemIdException(itemId));

        itemRepository.deleteById(itemEntity.getItemId());
    }
}
