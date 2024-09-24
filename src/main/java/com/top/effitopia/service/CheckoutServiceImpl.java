package com.top.effitopia.service;

import com.top.effitopia.domain.Checkout;
import com.top.effitopia.dto.CheckoutDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.mapper.CheckoutMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutMapper checkoutMapper;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<CheckoutDTO> getList(PageRequestDTO pageRequestDTO) {
        List<Checkout> checkoutList = checkoutMapper.selectList(pageRequestDTO);
        List<CheckoutDTO> checkoutDTOList = checkoutList.stream().map(vo->modelMapper.map(vo, CheckoutDTO.class)).toList();
        int total = checkoutMapper.getCount(pageRequestDTO);
        return PageResponseDTO
                .<CheckoutDTO>withAll()
                .dtoList(checkoutDTOList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}
