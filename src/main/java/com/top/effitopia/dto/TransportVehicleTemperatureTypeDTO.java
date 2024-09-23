package com.top.effitopia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransportVehicleTemperatureTypeDTO {
    private Integer transportVehicleTemperatureTypeId;
    private String transportVehicleTemperatureTypeName;
}
