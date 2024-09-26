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
    private String subclassCategoryId;
    private String name;
    private String productBrand;
    private ProductStorageType productStorageType;
    private String productCost;
    private String productSelling;
    private String productInfo;
    private String inboundBoxWidth;
    private String inboundBoxLength;
    private String InboundBoxHeight;
    private Long productWeight;
    private String productImg;


}
