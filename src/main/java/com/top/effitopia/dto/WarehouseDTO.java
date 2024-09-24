package com.top.effitopia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.top.effitopia.enumeration.WarehouseType;
import jakarta.annotation.Nullable;
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
public class WarehouseDTO {
    private long warehouse_id;
    @Nullable
    private MemberDTO memberDTO;
    @NotEmpty
    private WarehouseType warehouse_type;
    private String warehouse_code;

    private String warehouse_name;
    @NotEmpty
    private String warehouse_phone;
    @NotEmpty
    private String warehouse_warehouse_zip_code;
    @NotEmpty
    private String warehouse_lot_number;
    @Nullable
    private String warehouse_detail_address;

    private int	warehouse_width;

    private int	warehouse_length;

    private int	warehouse_height;
    @NotNull
    private int	warehouse_capacity;
    private double warehouse_latitude;
    private double warehouse_longitude;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;
}
