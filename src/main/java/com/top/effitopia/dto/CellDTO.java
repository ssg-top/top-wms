package com.top.effitopia.dto;

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

    private Integer id;

    private WarehouseDTO warehouseDTO;

    private String code;

    private int	width;

    private int	length;

    private int	height;

    @NotNull
    private int	capacity;
}
