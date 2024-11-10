package com.study.mall.item.controller;

import com.study.mall.common.dto.PageResponseDto;
import com.study.mall.common.dto.ResponseDto;
import com.study.mall.common.enums.ItemResponse;
import com.study.mall.item.dto.request.CreateItemRequestDto;
import com.study.mall.item.dto.request.UpdateItemRequestDto;
import com.study.mall.item.dto.response.GetItemResponseDto;
import com.study.mall.item.service.ItemService;
import com.study.mall.item.vo.ItemVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    /**
     * 상품 등록 앤드 포인트
     * @param createItemRequestDto 상품 등록 정보
     * @return 성공 응답 코드 & 응답 메시지
     */
    @PostMapping("")
    public ResponseEntity<ResponseDto<Map<String, Object>>> createItem(@RequestBody CreateItemRequestDto createItemRequestDto) {

        itemService.createItem(
                createItemRequestDto.getItemName(),
                createItemRequestDto.getItemDescription(),
                createItemRequestDto.getPrice());

        return ResponseEntity.ok().body(ItemResponse.ITEM_CREATE_ITEM_SUCCESS.toResponseDto());
    }

    /**
     * 상품 다중 조회 앤드 포인트
     * @param page 페이지
     * @param size 사이즈
     * @param orderBy 정렬 조건
     * @param order 정렬 방향
     * @return 성공 응답 코드, 메시지, 상품 정보 목록
     */
    @GetMapping("")
    public ResponseEntity<ResponseDto<PageResponseDto<List<GetItemResponseDto>>>> getItems(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "itemId") String orderBy,
            @RequestParam(value = "order", defaultValue = "desc") String order
    ) {

        PageResponseDto<List<ItemVo>> itemPageVo = itemService.getItems(page, size, orderBy, order);

        List<GetItemResponseDto> getItemResponseDtos = itemPageVo.getContent().stream()
                .map(itemVo -> GetItemResponseDto.builder()
                        .itemName(itemVo.getItemName())
                        .itemDescription(itemVo.getItemDescription())
                        .price(itemVo.getPrice())
                        .createdDt(itemVo.getCreatedDt())
                        .updatedDt(itemVo.getUpdatedDt())
                        .build())
                .toList();

        PageResponseDto<List<GetItemResponseDto>> getItemPageResponseDtos = PageResponseDto.<List<GetItemResponseDto>>builder()
                .content(getItemResponseDtos)
                .totalCount(itemPageVo.getTotalCount())
                .pageNo(itemPageVo.getPageNo())
                .pageSize(itemPageVo.getPageSize())
                .totalPages(itemPageVo.getTotalPages())
                .isLastPage(itemPageVo.getIsLastPage())
                .build();

        return ResponseEntity.ok().body(ItemResponse.ITEM_GET_ITEM_SUCCESS.toResponseDto(getItemPageResponseDtos));
    }

    /**
     * 상품 단건 조회 앤드 포인트
     * @param itemId 상품 ID
     * @return 성공 응답 코드, 메시지, 상품 정보
     */
    @GetMapping("/{itemId}")
    public ResponseEntity<ResponseDto<GetItemResponseDto>> getItem(@PathVariable("itemId") Long itemId) {

        ItemVo itemVo = itemService.getItem(itemId);

        GetItemResponseDto getItemResponseDto = GetItemResponseDto.builder()
                .itemName(itemVo.getItemName())
                .itemDescription(itemVo.getItemDescription())
                .price(itemVo.getPrice())
                .createdDt(itemVo.getCreatedDt())
                .updatedDt(itemVo.getUpdatedDt())
                .build();

        return ResponseEntity.ok().body(ItemResponse.ITEM_GET_ITEM_SUCCESS.toResponseDto(getItemResponseDto));
    }

    /**
     * 상품 수정 앤드 포인트
     * @param itemId 상품 ID
     * @param updateItemRequestDto 상품 수정 정보
     * @return 성공 응답 코드 & 응답 메시지
     */
    @PutMapping("/{itemId}")
    public ResponseEntity<ResponseDto<Map<String, Object>>> updateItem(
            @PathVariable("itemId") Long itemId,
            @RequestBody UpdateItemRequestDto updateItemRequestDto
    ) {

        itemService.updateItem(
                itemId,
                updateItemRequestDto.getItemName(),
                updateItemRequestDto.getItemDescription(),
                updateItemRequestDto.getPrice());

        return ResponseEntity.ok().body(ItemResponse.ITEM_UPDATE_ITEM_SUCCESS.toResponseDto());
    }

    /**
     * 상품 삭제 앤드 포인트
     * @param itemId 상품 ID
     * @return 성공 응답 코드 & 응답 메시지
     */
    @DeleteMapping("/{itemId}")
    public ResponseEntity<ResponseDto<Map<String, Object>>> deleteItem(@PathVariable("itemId") Long itemId) {

        itemService.deleteItem(itemId);

        return ResponseEntity.ok().body(ItemResponse.ITEM_DELETE_ITEM_SUCCESS.toResponseDto());
    }
}
