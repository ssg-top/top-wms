package com.top.effitopia.dto;

import com.top.effitopia.domain.Qr;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QrDTO {

    private Integer qrId;   //QR 아이디
    private InboundDTO inboundDTO;  //입고 DTO
    private String qrImg;   //QR 이미지(주소)

    public Qr toEntity() {
        return Qr.builder()
            .qrId(this.qrId)
            .inbound(this.inboundDTO.toEntity(inboundDTO.getMemberDTO().getId(), inboundDTO.getWarehouseDTO().getId()))
            .qrImg(this.qrImg)
            .build();
    }

    public static QrDTO fromEntity(Qr qr) {
        return QrDTO.builder()
            .qrId(qr.getQrId())
            .inboundDTO(InboundDTO.fromEntity(qr.getInbound()))  // Inbound -> InboundDTO 변환
            .qrImg(qr.getQrImg())
            .build();
    }


}
