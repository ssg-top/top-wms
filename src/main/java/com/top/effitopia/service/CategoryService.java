package com.top.effitopia.service;

import com.top.effitopia.domain.ProductMajorCategory;
import com.top.effitopia.domain.ProductMiddleCategory;
import com.top.effitopia.domain.ProductSubclassCategory;
import com.top.effitopia.mapper.ProductCategoryMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ProductCategoryMapper productCategoryMapper;

    public List<ProductMajorCategory> getAllMajorCategories() {
        return productCategoryMapper.selectAllMajorCategories();
    }

    public List<ProductMiddleCategory> getAllMiddleCategories() {
        return productCategoryMapper.selectAllMiddleCategories();
    }

    public List<ProductSubclassCategory> getAllSubclassCategories() {
        return productCategoryMapper.selectAllSubclassCategories();
    }
}
