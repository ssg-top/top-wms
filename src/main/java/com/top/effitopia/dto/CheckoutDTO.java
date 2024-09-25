package com.top.effitopia.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CheckoutDTO {
    private Integer checkoutId;
    private WarehouseDTO warehouseDTO;
    private LocalDateTime regDate;
    @NotEmpty(message = "창고코드는 비어 있을 수 없습니다!")
    private String checkoutWarehouseCode;
    @NotEmpty(message = "점검자명은 비어 있을 수 없습니다!")
    private String checkoutName;
}
