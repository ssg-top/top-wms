package com.top.effitopia.mapper;

import com.top.effitopia.domain.Product;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.ProductSearchCond;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

@Mapper
public interface ProductMapper {

    List<Product> selectAllProducts(PageRequestDTO<ProductSearchCond> pageRequestDTO);


    int getCount(ProductSearchCond searchCond);

}
