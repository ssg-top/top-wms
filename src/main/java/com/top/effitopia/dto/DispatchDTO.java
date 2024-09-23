package com.top.effitopia.dto;

import com.top.effitopia.domain.TransportVehicle;
import com.top.effitopia.enumeration.DispatchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DispatchDTO {
    private Integer dispatchId;
    private TransportVehicleDTO transportVehicleDTO;
    private DispatchStatus dispatchStatus;
    private OutboundDTO outboundDTO;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
