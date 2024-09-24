package com.top.effitopia.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {

    private int vendorId;   //거래처아이디
    private String vendorName;  //거래처명
    private Inbound inbound;    //입고아이디

}
