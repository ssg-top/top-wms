package com.top.effitopia.dto;

import com.top.effitopia.enumeration.WaybillStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WaybillDTO {
    private Integer waybillId;
    private WaybillStatus waybillStatus;
    private String waybillNum;
    private double waybillPrice;
    private LocalDateTime estimatedArrivalDate;
    private LocalDateTime regDate;
}
