package com.top.effitopia.dto;


import com.top.effitopia.domain.Product;
import com.top.effitopia.domain.ProductSubclassCategory;
import com.top.effitopia.enumeration.ProductStorageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Integer id;
    private ProductSubclassCategory productSubclassCategory;
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

    public Product toEntity() {
        return Product.builder()
            .id(this.id)
            .productSubclassCategory(ProductSubclassCategory.builder()
                .id(this.productSubclassCategory.getId())
                .name(this.productSubclassCategory.getName())
                .build())
            .name(this.name)
            .productBrand(this.productBrand)
            .productStorageType(this.productStorageType)
            .productCost(this.productCost)
            .productSelling(this.productSelling)
            .productInfo(this.productInfo)
            .inboundBoxWidth(this.inboundBoxWidth)
            .inboundBoxLength(this.inboundBoxLength)
            .inboundBoxHeight(this.inboundBoxHeight)
            .productWeight(this.productWeight)
            .productImg(this.productImg)
            .build();
    }

    public static ProductDTO fromEntity(Product product) {
        return ProductDTO.builder()
            .id(product.getId())
            .productSubclassCategory(ProductSubclassCategory.builder()
                .id(product.getProductSubclassCategory().getId())
                .name(product.getProductSubclassCategory().getName())
                .build())
            .name(product.getName())
            .productBrand(product.getProductBrand())
            .productStorageType(product.getProductStorageType())
            .productCost(product.getProductCost())
            .productSelling(product.getProductSelling())
            .productInfo(product.getProductInfo())
            .inboundBoxWidth(product.getInboundBoxWidth())
            .inboundBoxLength(product.getInboundBoxLength())
            .inboundBoxHeight(product.getInboundBoxHeight())
            .productWeight(product.getProductWeight())
            .productImg(product.getProductImg())
            .build();
    }

}
