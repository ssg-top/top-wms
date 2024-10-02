package com.top.effitopia.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.top.effitopia.dto.InboundDTO;
import com.top.effitopia.dto.MemberDTO;
import com.top.effitopia.dto.ProductDTO;
import com.top.effitopia.dto.VendorDTO;
import com.top.effitopia.dto.WarehouseDTO;
import com.top.effitopia.service.QrService;
import java.io.File;
import java.time.LocalDate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Transactional
@SpringBootTest
class QrMapperTest {

    @Autowired
    private QrMapper qrMapper;

    @Autowired
    private QrService qrService;

    private InboundDTO inboundDTO;

    @BeforeEach
    void setUp() {
        // InboundDTO 객체 세팅
        ProductDTO productDTO = ProductDTO.builder()
            .id(1)
            .name("Test Product")
            .productBrand("Test Brand")
            .build();

        MemberDTO memberDTO = MemberDTO.builder()
            .id(1)
            .username("testUser")
            .build();

        WarehouseDTO warehouseDTO = WarehouseDTO.builder()
            .id(1)
            .roadName("Test Road")
            .build();

        VendorDTO vendorDTO = VendorDTO.builder()
            .id(1)
            .name("Test Vendor")
            .build();

        inboundDTO = InboundDTO.builder()
            .id(1)
            .productDTO(productDTO)
            .memberDTO(memberDTO)
            .warehouseDTO(warehouseDTO)
            .vendorDTO(vendorDTO)
            .productQuantity(100)
            .inboundRequestDate(LocalDate.now())
            .inboundApprovedDate(LocalDate.now().plusDays(1))
            .inboundExpectDate(LocalDate.now().plusDays(3))
            .delegateRequesterId(123)
            .build();
    }


    @Test
    void QR_생성() {

        String qrImgPath = qrService.generateQrCodeImage(inboundDTO);

        // QR 이미지 파일이 정상적으로 생성되었는지 확인
        assertNotNull(qrImgPath);
        File qrFile = new File(qrImgPath);
        assertTrue(qrFile.exists());

        log.info("QR 생성 경로: {}", qrImgPath);
    }

    @Test
    void QR_조회() {
    }
}