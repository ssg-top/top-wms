package com.top.effitopia.dto;

import com.top.effitopia.domain.Address;
import com.top.effitopia.domain.WarehouseType;
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

    private Integer id;

    private MemberDTO memberDTO;

    @NotEmpty
    private WarehouseTypeDTO warehouseType;

    private String code;

    private String name;

    @NotEmpty
    private String phone;

    private String zipCode;

    private String roadName;

    private String lotNumber;

    private String detailAddress;

    private int	width;

    private int	length;

    private int	height;

    @NotNull
    private int	capacity;

    private double latitude;

    private double longitude;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
