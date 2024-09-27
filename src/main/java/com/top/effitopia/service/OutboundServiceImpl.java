package com.top.effitopia.service;

import com.top.effitopia.domain.Order;
import com.top.effitopia.domain.Outbound;
import com.top.effitopia.dto.OrderDTO;
import com.top.effitopia.dto.OutboundDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.enumeration.OutboundStatus;
import com.top.effitopia.mapper.OrderMapper;
import com.top.effitopia.mapper.OutboundMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class OutboundServiceImpl implements OutboundService {

    private final OrderMapper orderMapper;
    private final OutboundMapper outboundMapper;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void registerOrderAndOutbound(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        orderMapper.insertOrder(order);

        Outbound outbound = Outbound.builder()
                .order(order)
                .outboundStatus(OutboundStatus.PENDING)
                .regDate(LocalDateTime.now())
                .build();
        outboundMapper.insertOutbound(outbound);
    }

    @Override
    public PageResponseDTO<OutboundDTO> getList(PageRequestDTO pageRequestDTO) {
        List<OutboundDTO> dtoList = outboundMapper.selectList(pageRequestDTO).stream()
                .map(outbound -> modelMapper.map(outbound, OutboundDTO.class))
                .collect(Collectors.toList());

        int total = outboundMapper.getTotalCount(pageRequestDTO);
        return new PageResponseDTO<>(pageRequestDTO, dtoList, total);
    }

    @Override
    public OutboundDTO getOutboundDetail(Integer outboundId) {
        Outbound outbound = outboundMapper.selectDetail(outboundId);
        return modelMapper.map(outbound, OutboundDTO.class);
    }

    @Override
    public void updateOutboundStatus(Integer outboundId, OutboundStatus status) {
        outboundMapper.updateStatus(outboundId, status);
    }

    @Transactional
    @Override
    public void updateOrder(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        orderMapper.updateOrder(order);
    }
}
