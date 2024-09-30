package com.top.effitopia.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.top.effitopia.domain.Inbound;
import com.top.effitopia.domain.Member;
import com.top.effitopia.domain.Product;
import com.top.effitopia.domain.Vendor;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.enumeration.InboundStatus;
import com.top.effitopia.service.InboundService;
import com.top.effitopia.service.QrService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@SpringBootTest
class InboundMapperTest {

    @Autowired
    private InboundMapper inboundMapper;

    @Autowired
    private QrMapper qrMapper;

    @Autowired
    private InboundService inboundService;
    @Autowired
    private QrService qrService;

    @Test
    void 사업자_입고요청_생성() {
        // Given
        Inbound inbound = Inbound.builder()
            .member(Member.builder()
                .id(3)
                .build())
            .warehouse(Warehouse.builder()
                .id(4)
                .build())
            .vendor(Vendor.builder()
                .id(3)
                .build())
            .product(Product.builder()
                .id(13)
                .build())
            .productQuantity(20)
            .inboundRequestDate(LocalDate.now())
            .inboundExpectDate(LocalDate.of(2024,10,4))
            .inboundStatus(InboundStatus.INBOUND_REQUESTED)
            .build();

        // WHEN
        int result = inboundMapper.insert(inbound);

        // THEN
        Assertions.assertThat(result).isEqualTo(1);
        log.info(result);
    }

    @Test
    void 관리자_입고요청_생성() {
        // GIVEN
        Inbound inbound = Inbound.builder()
            .member(Member.builder()
                .id(3)
                .build())
            .warehouse(Warehouse.builder()
                .id(4)
                .build())
            .vendor(Vendor.builder()
                .id(3)
                .build())
            .product(Product.builder()
                .id(5)
                .build())
            .productQuantity(20)
            .inboundRequestDate(LocalDate.now())
            .inboundExpectDate(LocalDate.now())
            .inboundStatus(InboundStatus.INBOUND_REQUESTED)
            .delegateRequesterId(3)
            .build();

        // WHEN
        int result = inboundMapper.insert(inbound);

        // THEN
        Assertions.assertThat(result).isEqualTo(1);
        log.info(result);
    }

    @Test
    void 입고요청_수정() {
        // Given
        Inbound inbound = Inbound.builder()
            .id(1)
            .productQuantity(10)
            .inboundExpectDate(LocalDate.of(2024,10,20))
            .build();

        // When
        int result = inboundMapper.update(inbound);

        // Then
        assertThat(result).isEqualTo(1);
        log.info(result);
    }

    @Test
    void 입고요청_리스트_승인() {
        // GIVEN
        List<Integer> inboundIds = List.of(1, 3);
        int result = inboundMapper.approveList(inboundIds);

        // THEN
        assertThat(result).isEqualTo(inboundIds.size());
        log.info("승인된 입고 요청 개수: " + result);
    }

    @Test
    void 입고요청_리스트_완료() {
        // GIVEN
        List<Integer> inboundIds = List.of(2, 5, 9, 10);

        // When
        int result = inboundMapper.completeList(inboundIds);

        // Then
        assertThat(result).isEqualTo(inboundIds.size());
        log.info("삭제된 입고 요청 개수: " + result);

    }

    @Test
    void 입고요청_상세조회() {
        Inbound inbound = Inbound.builder()
            .id(2)
            .build();
    }

    @Test
    void 전체_입고요청_개수() {
        // GIVEN
        List<Inbound> inboundList = inboundMapper.selectAllList();

        // THEN
        log.info("입고 조회 : " + inboundList.size());
    }


    @Test
    void 입고요청_리스트_삭제() {
        // Given
        List<Integer> inboundIds = List.of(44);

        // When
        int result = inboundMapper.deleteList(inboundIds);

        // Then
        assertThat(result).isEqualTo(inboundIds.size());
        log.info("삭제된 입고 요청 개수: " + result);
    }

    @Test
    void 입고요청_완료_및_QR코드_생성() {
        // Given
        Inbound inbound = Inbound.builder()
            .member(Member.builder().id(3).build())
            .warehouse(Warehouse.builder().id(4).build())
            .vendor(Vendor.builder().id(3).build())
            .product(Product.builder().id(13).build())
            .productQuantity(20)
            .inboundRequestDate(LocalDate.now())
            .inboundExpectDate(LocalDate.of(2024,10,4))
            .inboundStatus(InboundStatus.INBOUND_REQUESTED)
            .build();

        inboundMapper.insert(inbound);

        // When: 입고 요청을 승인하고 QR 코드가 생성되는지 확인
        List<Integer> inboundIds = List.of(inbound.getId());
        boolean success = inboundService.approveInboundRequests(inboundIds);

        // Then: QR 코드가 성공적으로 생성되었는지 확인
        assertThat(success).isTrue();
        assertThat(qrService.get(inbound.getId()).isPresent()).isTrue();
        log.info("QR 코드가 생성되었습니다: " + qrService.get(inbound.getId()));
    }

    @Test
    void existsByXxx() {
    }
}