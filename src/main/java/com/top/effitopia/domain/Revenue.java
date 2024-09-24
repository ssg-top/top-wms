package com.top.effitopia.domain;

import com.top.effitopia.enumeration.RevenueCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Revenue {

    private Integer id;
    private Warehouse warehouse;
    private Member member;
    private RevenueCategory category;
    private double amount;
    private String revenueDetails;
    private LocalDateTime requestDate;
    private LocalDateTime completeDate;
    private String paymentMethod;
    private boolean isPaid;
    private boolean delFlag;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
