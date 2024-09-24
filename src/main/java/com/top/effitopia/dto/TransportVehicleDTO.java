package com.top.effitopia.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransportVehicleDTO {
    private Integer id;
    private MemberDTO memberDTO;
    private TransportVehicleCarTypeDTO transportVehicleCarTypeDTO;
    private TransportVehicleTemperatureTypeDTO transportVehicleTemperatureTypeDTO;
    @NotEmpty
    private String carNum;
}
