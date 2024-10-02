package com.top.effitopia.dto;

import com.top.effitopia.domain.Inbound;
import com.top.effitopia.domain.Member;
import com.top.effitopia.domain.Product;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.enumeration.InboundStatus;
import com.top.effitopia.exception.FutureDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
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

    private Integer id;
    private MemberDTO memberDTO;
    private WarehouseDTO warehouseDTO;
    private VendorDTO vendorDTO;
    private ProductDTO productDTO;

    @NotNull(message = "상품수량은 필수 입력 항목입니다.")
    //@Pattern(regexp = "\\d+",message = "숫자만 입력해주세요.")
    @Positive(message = "0 이상의 수를 입력해주세요.")
    private Integer productQuantity;
    private LocalDate inboundRequestDate;
    private LocalDate inboundApprovedDate;

    @FutureDate
    private LocalDate inboundExpectDate;

    private LocalDate inboundCompletedDate;
    private LocalDate inboundCanceledDate;
    private InboundStatus inboundStatus;
    private Integer delegateRequesterId;

    public Inbound toEntity(int memberId, int warehouseId) {
        return Inbound.builder()
            .id(this.id)
            .member(Member.builder()
                .id(memberId)
                .build())
            .warehouse(Warehouse.builder()
                .id(warehouseId)
                .build())
            .vendor(this.vendorDTO != null ? this.vendorDTO.toEntity() : null)  // Null 처리
            .product(this.productDTO != null ? this.productDTO.toEntity() : null)  // Null 처리
            .productQuantity(this.productQuantity)
            .inboundRequestDate(this.inboundRequestDate)
            .inboundApprovedDate(this.inboundApprovedDate)
            .inboundExpectDate(this.inboundExpectDate)
            .inboundCompletedDate(this.inboundCompletedDate)
            .inboundCanceledDate(this.inboundCanceledDate)
            .inboundStatus(this.inboundStatus)
            .delegateRequesterId(this.delegateRequesterId)
            .build();
    }

    public static InboundDTO fromEntity(Inbound inbound) {
        return InboundDTO.builder()
            .id(inbound.getId())
            .memberDTO(inbound.getMember() != null ? MemberDTO.builder()
                .id(inbound.getMember().getId())
                .name(inbound.getMember().getName())
                .zipCode(inbound.getMember().getAddress() != null ? inbound.getMember().getAddress().getZipCode() : null)
                .build() : null)
            .warehouseDTO(inbound.getWarehouse() != null ? WarehouseDTO.builder()
                .id(inbound.getWarehouse().getId())
                .build() : null)
            .vendorDTO(inbound.getVendor() != null ? VendorDTO.fromEntity(inbound.getVendor()) : null)  // Null 처리
            .productDTO(inbound.getProduct() != null ? ProductDTO.fromEntity(inbound.getProduct()) : null)  // Null 처리
            .productQuantity(inbound.getProductQuantity())
            .inboundRequestDate(inbound.getInboundRequestDate())
            .inboundApprovedDate(inbound.getInboundApprovedDate())
            .inboundExpectDate(inbound.getInboundExpectDate())
            .inboundCompletedDate(inbound.getInboundCompletedDate())
            .inboundCanceledDate(inbound.getInboundCanceledDate())
            .inboundStatus(inbound.getInboundStatus())
            .delegateRequesterId(inbound.getDelegateRequesterId())
            .build();
    }
}
