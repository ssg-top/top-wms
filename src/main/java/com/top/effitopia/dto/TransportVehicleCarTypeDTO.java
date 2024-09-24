package com.top.effitopia.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotEmpty
    private String transportVehicleCarTypeName;
    @NotNull
    @Positive
    private Integer transportVehicleHeight;
    @NotNull
    @Positive
    private Integer transportVehicleLength;
    @NotNull
    @Positive
    private Integer transportVehicleWidth;
    @NotNull
    @Positive
    private Integer transportVehicleMaximumLoad;
}
