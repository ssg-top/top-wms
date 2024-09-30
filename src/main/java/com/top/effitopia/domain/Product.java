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

    private Integer id;
    private ProductSubclassCategory productSubclassCategory; // 객체 전체를 받음
    private String name;
    private String productBrand;
    private ProductStorageType productStorageType;
    private Integer productCost;
    private Integer productSelling;
    private String productInfo;
    private Integer inboundBoxWidth;
    private Integer inboundBoxLength;
    private Integer inboundBoxHeight;
    private Long productWeight;
    private String productImg;


}
