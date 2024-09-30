package com.top.effitopia.enumeration;

import lombok.Getter;

@Getter
public enum ProductStorageType {

    FROZEN("냉동"), //냉동
    REFRIGERATED("냉장"),   //냉장
    AMBIENT("상온"); //상온


    private final String value;

    ProductStorageType(String value) {
        this.value = value;
    }

}
