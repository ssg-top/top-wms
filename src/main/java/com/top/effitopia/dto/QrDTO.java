package com.top.effitopia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QrDTO {

    private int qrId;   //QR 아이디
    private InboundDTO inboundDTO;  //입고 DTO
    private String qrImg;   //QR 이미지(주소)

}
