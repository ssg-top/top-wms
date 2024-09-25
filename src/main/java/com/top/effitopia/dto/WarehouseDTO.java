package com.top.effitopia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.top.effitopia.domain.Address;
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

    private Integer id;

    @Nullable
    private MemberDTO memberDTO;

    @NotEmpty
    private WarehouseType type;

    private String code;

    private String name;

    @NotEmpty
    private String phone;

    private Address address;

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
