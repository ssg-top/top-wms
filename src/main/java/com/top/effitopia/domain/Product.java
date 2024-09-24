package com.top.effitopia.domain;

import com.top.effitopia.enumeration.ProductStorageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private int productId;               //상품아이디
    private String subclassCategory;    //소분류
    private String productName;         //상품명
    private String productBrand;        //상품브랜드
    private ProductStorageType productStorageType;  //상품보관타입
    private String productCost;         //상품원가
    private String productSelling;      //상품판매가
    private String productInfo;         //상품정보
    private String inboundBoxWidth;     //입고박스너비
    private String inboundBoxLength;    //입고박스길이
    private String InboundBoxHeight;    //입고박스높이
    private Long productWeight;         //상품무게
    private String productImg;          //상품이미지(주소)


}
