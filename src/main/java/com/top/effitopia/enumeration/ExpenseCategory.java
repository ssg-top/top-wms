package com.top.effitopia.enumeration;

import lombok.Getter;

@Getter
public enum ExpenseCategory {
    MAINTENANCE_COST("유지보수비"),
    PERSONAL_COST("인건비"),
    ETC("기타");

    private final String value;

    ExpenseCategory(String value) {
        this.value = value;
    }
}
