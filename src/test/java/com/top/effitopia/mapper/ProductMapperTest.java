package com.top.effitopia.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.top.effitopia.domain.Product;
import com.top.effitopia.dto.ProductDTO;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void selectAllProducts() {
        List<Product> products = productMapper.selectAllProducts();
        log.info("product Test:  " + products.size());
    }

    @Test
    void selectList() {
    }
}