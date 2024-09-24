package com.top.effitopia.domain;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransportVehicleCarType {
    private Integer transportVehicleCarTypeId;
    private String transportVehicleCarTypeName;
    private Integer transportVehicleHeight;
    private Integer transportVehicleLength;
    private Integer transportVehicleWidth;
    private Integer transportVehicleMaximumLoad;
}
