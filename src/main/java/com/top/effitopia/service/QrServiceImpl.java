package com.top.effitopia.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.top.effitopia.domain.Qr;
import com.top.effitopia.dto.InboundDTO;
import com.top.effitopia.dto.QrDTO;
import com.top.effitopia.mapper.QrMapper;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class QrServiceImpl implements QrService {

    private final QrMapper qrMapper;

    @Override
    public void save (QrDTO qrDTO) {
        qrMapper.insert(qrDTO.toEntity());
    }

    @Override
    public Optional<QrDTO> get(int qrId) {
        Optional<Qr> getQr = qrMapper.selectOne(qrId);
        return getQr.map(QrDTO::fromEntity);
    }

    @Override
    public String generateQrCodeImage(InboundDTO inboundDTO) {

        String qrData = new StringBuilder()
            .append("Inbound ID: ").append(inboundDTO.getId()).append("\n")
            .append("Brand: ").append(inboundDTO.getProductDTO().getProductBrand()).append("\n")
            .append("Product: ").append(inboundDTO.getProductDTO().getName()).append("\n")
            .append("Quantity: ").append(inboundDTO.getProductQuantity()).append("\n")
            .append("Member: ").append(inboundDTO.getMemberDTO().getUsername()).append("\n")
            .append("Warehouse Location: ").append(inboundDTO.getWarehouseDTO().getRoadName()).append("\n")
            .append("Vendor: ").append(inboundDTO.getVendorDTO().getName()).append("\n")
            .append("Inbound Request Date: ").append(inboundDTO.getInboundRequestDate()).append("\n")
            .append("Inbound Approved Date: ").append(inboundDTO.getInboundApprovedDate()).append("\n")
            .append("Inbound Expect Date: ").append(inboundDTO.getInboundExpectDate()).append("\n")
            .append("Delegate Requester: ").append(
                inboundDTO.getDelegateRequesterId() != null ? inboundDTO.getDelegateRequesterId() : "N/A")
            .toString();
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(qrData, BarcodeFormat.QR_CODE, 300, 300);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            String qrImgPath = "/img/qr/inbound_" + inboundDTO.getId() + ".png";
            ImageIO.write(qrImage, "png", new File(qrImgPath));

            return qrImgPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
