package com.top.effitopia.dto;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String transportVehicleTemperatureTypeName;
}
