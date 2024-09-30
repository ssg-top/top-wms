package com.top.effitopia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailsDTO {
    private OrderDTO orderDTO;
    private OutboundDTO outboundDTO;
    private DispatchDTO dispatchDTO;
    private WaybillDTO waybillDTO;
}
