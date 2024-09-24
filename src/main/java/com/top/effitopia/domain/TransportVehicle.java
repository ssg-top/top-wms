package com.top.effitopia.domain;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransportVehicle {
    private Integer id;
    private Member member;
    private TransportVehicleCarType transportVehicleCarType;
    private TransportVehicleTemperatureType transportVehicleTemperatureType;
    private String carNum;
}
