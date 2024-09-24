package com.top.effitopia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private long contract_id;
    @NotEmpty
    private WarehouseDTO warehouseDTO;
    @NotEmpty
    private MemberDTO memberDTO;
    @NotEmpty
    private ContractStatus contract_status;
    private LocalDateTime contract_start_date;
    @NotNull
    private int contract_date;
    private LocalDateTime contract_end_date;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;
}
