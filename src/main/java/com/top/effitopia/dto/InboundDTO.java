package com.top.effitopia.dto;

import com.top.effitopia.domain.Inbound;
import com.top.effitopia.domain.Member;
import com.top.effitopia.domain.Product;
import com.top.effitopia.domain.Warehouse;
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
    private VendorDTO vendorDTO;
    private LocalDate inboundRequestDate;
    private LocalDate inboundApprovedDate;
    private LocalDate inboundExpectDate;
    private LocalDate inboundCompletedDate;
    private InboundStatus inboundStatus;

/*    private Inbound toInboundDTO(int memberId) {
        return Inbound.builder()
            .inboundId(this.inboundId)
            .member(Member.builder()
                .id(memberId)
                .build())
            .warehouse(Warehouse.builder()
                .warehouse_id(warehouseId)
                .build())
            .product(Product.builder()
                .
                .build())
            .inboundRequestDate(inboundDTO.getInboundRequestDate())
            .inboundApprovedDate(inboundDTO.getInboundApprovedDate())
            .inboundExpectDate(inboundDTO.getInboundExpectDate())
            .inboundCompletedDate(inboundDTO.getInboundCompletedDate())
            .inboundStatus(inboundDTO.getInboundStatus())
            .build();
    }

    private InboundDTO toInbound(Inbound inbound) {
        return InboundDTO.builder()
            .inboundId(inbound.getInboundId())
            .member(inbound.getMember())
            .warehouse(inbound.getWarehouse())
            .product(inbound.getProduct())
            .inboundRequestDate(inbound.getInboundRequestDate())
            .inboundApprovedDate(inbound.getInboundApprovedDate())
            .inboundExpectDate(inbound.getInboundExpectDate())
            .inboundCompletedDate(inbound.getInboundCompletedDate())
            .inboundStatus(inbound.getInboundStatus())
            .build();
    }*/


}
