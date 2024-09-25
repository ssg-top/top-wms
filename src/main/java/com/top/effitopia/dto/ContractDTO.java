package com.top.effitopia.dto;

import com.top.effitopia.enumeration.ContractStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {

    private Integer id;

    @NotEmpty
    private WarehouseDTO warehouseDTO;

    @NotEmpty
    private MemberDTO memberDTO;

    @NotEmpty
    private ContractStatus status;

    private LocalDateTime startDate;

    @NotNull
    private int date;

    private LocalDateTime endDate;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
