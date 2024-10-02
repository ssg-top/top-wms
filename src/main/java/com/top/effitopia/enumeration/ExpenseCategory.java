package com.top.effitopia.enumeration;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ExpenseCategory {
    MAINTENANCE_COST("유지보수비"),
    PERSONAL_COST("인건비"),
    ETC("기타");

    public static final Map<String, String> CATEGORY_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(ExpenseCategory::getValue, ExpenseCategory::name)));
    private final String value;

    ExpenseCategory(String value) {
        this.value = value;
    }
}
