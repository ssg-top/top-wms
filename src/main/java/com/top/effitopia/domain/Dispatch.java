package com.top.effitopia.domain;

import com.top.effitopia.enumeration.DispatchStatus;
import lombok.*;
import org.apache.tomcat.util.net.DispatchType;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dispatch {
    private Integer dispatchId;
    private TransportVehicle transportVehicle;
    private DispatchStatus dispatchStatus;
    private Outbound outbound;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
