package com.top.effitopia.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String zipCode;
    private String roadNameAddress;
    private String lotNumberAddress;
    private String detailAddress;
}
