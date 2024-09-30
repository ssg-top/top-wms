package com.top.effitopia.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.top.effitopia.domain.Product;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.ProductDTO;
import com.top.effitopia.dto.ProductSearchCond;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Transactional
@SpringBootTest
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void 상품조회() {
        // Given
        ProductSearchCond searchCond = ProductSearchCond.builder()
            .productName(null)
            .productBrand(null)
            .majorCategoryId(2)
            .middleCategoryId(null)
            .subclassCategoryId(null)
            .build();

        PageRequestDTO<ProductSearchCond> pageRequestDTO = PageRequestDTO.<ProductSearchCond>builder()
            .page(2)
            .size(15)
            .searchCond(searchCond)
            .build();

        // When
        List<Product> products = productMapper.selectAllProducts(pageRequestDTO);

        // Then
        log.info("조회된 상품 개수: " + products.size());
        products.forEach(product -> log.info(product));
    }


    @Test
    void 검색조건_상품개수() {
        // Given
        ProductSearchCond searchCond = ProductSearchCond.builder()
            //.productName("과일")
            .build();

        // When
        int totalCount = productMapper.getCount(searchCond);

        // Then
        log.info("검색 조건으로 조회된 총 상품 개수: " + totalCount);
    }

    @Test
    void selectList() {
    }
}