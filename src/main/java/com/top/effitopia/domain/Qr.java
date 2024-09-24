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
public class Qr {

    private int qrId;   //QR 아이디
    private Inbound inbound;    //입고 도메인
    private String qrImg;   //QR 이미지(주소)

}
