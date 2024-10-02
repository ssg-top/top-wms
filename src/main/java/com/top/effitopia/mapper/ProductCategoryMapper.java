package com.top.effitopia.mapper;

import com.top.effitopia.domain.ProductMajorCategory;
import com.top.effitopia.domain.ProductMiddleCategory;
import com.top.effitopia.domain.ProductSubclassCategory;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

// ProductCategoryMapper.java
@Mapper
public interface ProductCategoryMapper {

    // 대분류 카테고리 조회
    List<ProductMajorCategory> selectAllMajorCategories();

    // 중분류 카테고리 조회
    List<ProductMiddleCategory> selectAllMiddleCategories();

    // 소분류 카테고리 조회
    List<ProductSubclassCategory> selectAllSubclassCategories();
}

