package com.top.effitopia.dto;

import com.top.effitopia.domain.Revenue;
import com.top.effitopia.enumeration.RevenueCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueDTO {

    private Integer id;
    private int warehouseId;
    private String warehouseCode;
    private int memberId;
    private RevenueCategory category;
    private double amount;
    private String revenueDetails;
    private LocalDateTime requestDate;
    private LocalDateTime completeDate;
    private String paymentMethod;
    private boolean paid;

    public static RevenueDTO from(Revenue vo) {
        return RevenueDTO.builder()
                .id(vo.getId())
                .warehouseId(vo.getWarehouse().getId())
                .warehouseCode(vo.getWarehouse().getCode())
                .memberId(vo.getMember().getId())
                .category(vo.getCategory())
                .amount(vo.getAmount())
                .revenueDetails(vo.getRevenueDetails())
                .requestDate(vo.getRequestDate())
                .completeDate(vo.getCompleteDate())
                .paymentMethod(vo.getPaymentMethod())
                .paid(vo.isPaid())
                .build();
    }

    public Revenue getPaymentInfo() {
        return Revenue.builder()
                .paid(this.paid)
                .completeDate(this.completeDate)
                .paymentMethod(this.paymentMethod)
                .build();
    }
}
