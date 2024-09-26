package com.top.effitopia.service;

import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    ModelMapper modelMapper;
    ProductMapper productMapper;

    @Override
    public void getList(PageRequestDTO pageRequestDTO) {

    }
}
