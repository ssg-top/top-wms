package com.top.effitopia.enumeration;

import lombok.Getter;

@Getter
public enum ContractStatus {
    APPROVE("승인"),
    REJECT("거절"),
    REQUEST("요청 중");

    private final String value;

    ContractStatus(String value) {
        this.value = value;
    }
}
