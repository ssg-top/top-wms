package com.top.effitopia.service;

import com.top.effitopia.domain.Product;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.ProductDTO;
import com.top.effitopia.dto.ProductSearchCond;
import com.top.effitopia.mapper.ProductMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO<ProductSearchCond> pageRequestDTO) {
        List<Product> products = productMapper.selectAllProducts(pageRequestDTO);

        int total = productMapper.getCount(pageRequestDTO.getSearchCond());

        List<ProductDTO> dtoList = products.stream()
            .map(ProductDTO::fromEntity)
            .toList();

        // PageResponseDTO 생성 후 반환
        return PageResponseDTO.<ProductDTO>withAll()
            .pageRequestDTO(pageRequestDTO)
            .dtoList(dtoList)
            .total(total)
            .build();
    }
}
