package com.top.effitopia.dto;

import com.top.effitopia.enumeration.WaybillStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotEmpty
    private String waybillNum;
    @NotNull
    @Positive
    private double waybillPrice;
    private LocalDateTime estimatedArrivalDate;
    private LocalDateTime regDate;
}
