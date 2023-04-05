package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

    private String searchDateType; //현재시간과 상품등록일을 비교

    private ItemSellStatus searchSellStatus; //판매상품 기준으로 조회

    private String searchBy; //어떤 유형으로 조회할지 ex)상품명 or 등록자

    private String searchQuery = "";
}
