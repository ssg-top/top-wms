package com.top.effitopia.enumeration;

import lombok.Getter;

@Getter
public enum MemberRole {

    ADMIN("총 관리자"),
    WAREHOUSE_MANAGER("창고 관리자"),
    BUSINESS_PROPRIETOR("사업자"),
    DISPATCHER("배송 기사");

    private final String value;

    MemberRole(String value) {
        this.value = value;
    }
}
