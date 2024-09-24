package com.top.effitopia.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CellDTO {
    private long cell_id;
    private WarehouseDTO warehouseDTO;
    private int	cell_code;
    private int	cell_width;
    private int	cell_length;
    private int	cell_height;
    @NotNull
    private int	cell_capacity;
}
