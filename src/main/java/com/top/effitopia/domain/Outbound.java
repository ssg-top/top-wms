package com.top.effitopia.domain;

import com.top.effitopia.enumeration.OutboundStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Outbound {
    private Integer outboundId;
    private OutboundStatus outboundStatus;
    private Order order;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
