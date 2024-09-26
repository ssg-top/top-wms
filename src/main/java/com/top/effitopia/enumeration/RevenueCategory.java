package com.top.effitopia.enumeration;

import lombok.Getter;

@Getter
public enum RevenueCategory {
    CONTRACT_COST("창고 계약비"),
    STORAGE_CHARGE("창고 보관료"),
    INBOUND_CHARGE("입고 수수료"),
    OUTBOUND_CHARGE("출고 수수료"),
    FREIGHT_CHARGE("운임료");

    private final String value;

    RevenueCategory(String value) {
        this.value = value;
    }
}
