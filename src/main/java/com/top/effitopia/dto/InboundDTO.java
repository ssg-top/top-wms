package com.top.effitopia.dto;

import com.top.effitopia.enumeration.InboundStatus;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InboundDTO {

    private int inboundId;
    private MemberDTO memberDTO;
    private WarehouseDTO warehouseDTO;
    private ProductDTO productDTO;
    private LocalDate inboundRequestDate;
    private LocalDate inboundApprovedDate;
    private LocalDate inboundExpectDate;
    private LocalDate inboundCompletedDate;
    private InboundStatus inboundStatus;

}
