package com.top.effitopia.service;

import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.ProductDTO;
import com.top.effitopia.dto.ProductSearchCond;

public interface ProductService {

    PageResponseDTO<ProductDTO> getList(PageRequestDTO<ProductSearchCond> pageRequestDTO);

}
