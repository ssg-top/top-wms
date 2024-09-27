package com.top.effitopia.domain;

import com.top.effitopia.enumeration.WaybillStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Waybill {
    private Integer waybillId;
    private Outbound outbound;
    private WaybillStatus waybillStatus;
    private String waybillNum;
    private double waybillPrice;
    private LocalDateTime estimatedArrivalDate;
    private LocalDateTime regDate;
}
