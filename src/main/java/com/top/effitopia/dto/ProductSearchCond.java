package com.top.effitopia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchCond {

    private String productName;
    private String productBrand;
    private Integer majorCategoryId;
    private Integer middleCategoryId;
    private Integer subclassCategoryId;

}