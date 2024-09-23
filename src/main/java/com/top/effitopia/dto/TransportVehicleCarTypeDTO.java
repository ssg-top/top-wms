package com.top.effitopia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransportVehicleCarTypeDTO {
    private Integer transportVehicleCarTypeId;
    private String transportVehicleCarTypeName;
    private Integer transportVehicleHeight;
    private Integer transportVehicleLength;
    private Integer transportVehicleWidth;
    private Integer transportVehicleMaximumLoad;
}
