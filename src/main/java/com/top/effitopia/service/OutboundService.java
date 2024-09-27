package com.top.effitopia.service;

import com.top.effitopia.dto.OrderDTO;
import com.top.effitopia.dto.OutboundDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.enumeration.OutboundStatus;

public interface OutboundService {
    void registerOrderAndOutbound(OrderDTO orderDTO);

    PageResponseDTO<OutboundDTO> getList(PageRequestDTO pageRequestDTO);

    OutboundDTO getOutboundDetail(Integer outboundId);

    void updateOutboundStatus(Integer outboundId, OutboundStatus status);

    void updateOrder(OrderDTO orderDTO);
}
