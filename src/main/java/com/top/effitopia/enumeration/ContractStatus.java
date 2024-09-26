package com.top.effitopia.enumeration;

import lombok.Getter;

@Getter
public enum ContractStatus {
    APPROVE("승인"),
    REJECT("요청 중"),
    REQUEST("거절");

    private final String value;

    ContractStatus(String value) {
        this.value = value;
    }
}
