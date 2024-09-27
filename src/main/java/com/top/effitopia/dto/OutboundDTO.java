package com.top.effitopia.dto;

import com.top.effitopia.enumeration.OutboundStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OutboundDTO {
    private Integer outboundId;
    private OutboundStatus outboundStatus;
    private OrderDTO orderDTO;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
