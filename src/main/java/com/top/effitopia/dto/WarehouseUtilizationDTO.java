package com.top.effitopia.dto;

import com.top.effitopia.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseUtilizationDTO {

    private int id;
    private String name;
    private String code;
    private String roadNameAddress;
    private String lotNumberAddress;
    private String detailAddress;
    private double latitude;
    private double longitude;
    private double utilization;

}
