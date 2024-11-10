package com.study.mall.item.service;

import com.study.mall.common.dto.PageResponseDto;
import com.study.mall.common.exception.NotExistsItemIdException;
import com.study.mall.item.dto.etc.ItemUpdateDto;
import com.study.mall.item.entity.ItemEntity;
import com.study.mall.item.repository.ItemRepository;
import com.study.mall.item.vo.ItemVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
     * 상품 다중 조회
     * @param page 페이지
     * @param size 사이즈
     * @param orderBy 정렬 조건
     * @param order 정렬 방향
     * @return 상품 정보 Vo 목록
     */
    @Transactional(readOnly = true)
    public PageResponseDto<List<ItemVo>> getItems(Integer page, Integer size, String orderBy, String order) {

        Sort sort = Sort.by(order.equalsIgnoreCase("desc") ? Sort.Order.desc(orderBy) : Sort.Order.asc(orderBy));

        Pageable pageable = PageRequest.of(Math.max(0, page - 1), size, sort);

        Page<ItemEntity> itemPageEntities = itemRepository.findAll(pageable);

        List<ItemVo> itemVos = itemPageEntities.stream()
                .map(itemEntity -> ItemVo.builder()
                        .itemName(itemEntity.getItemName())
                        .itemDescription(itemEntity.getItemDescription())
                        .price(itemEntity.getPrice())
                        .createdDt(itemEntity.getCreatedDt())
                        .updatedDt(itemEntity.getUpdatedDt())
                        .build())
                .toList();

        return PageResponseDto.<List<ItemVo>>builder()
                .content(itemVos)
                .totalCount(itemPageEntities.getTotalElements())
                .pageNo(itemPageEntities.getNumber() + 1)
                .pageSize(itemPageEntities.getSize())
                .totalPages(itemPageEntities.getTotalPages())
                .isLastPage(itemPageEntities.isLast())
                .build();
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
