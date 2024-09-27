package com.top.effitopia.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.top.effitopia.domain.Inbound;
import com.top.effitopia.domain.Member;
import com.top.effitopia.domain.Product;
import com.top.effitopia.domain.Vendor;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.enumeration.InboundStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Transactional
@SpringBootTest
class InboundMapperTest {

    @Autowired
    private InboundMapper inboundMapper;

    @Test
    void 입고요청_생성() {
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
            .inboundRequestDate(LocalDate.now())
            .inboundExpectDate(LocalDate.now())
            .inboundStatus(InboundStatus.INBOUND_REQUESTED)
            .build();

        // WHEN
        int result = inboundMapper.insert(inbound);

        // THEN
        Assertions.assertThat(result).isEqualTo(1);
        log.info(result);
    }

    @Test
    void 입고요청_수정() {
        Inbound inbound = Inbound.builder()
            .id(1)
            .inboundExpectDate(LocalDate.now())
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
        Inbound inbound1 = Inbound.builder()
            .member(Member.builder()
                .id(2)
                .build())
            .warehouse(Warehouse.builder()
                .id(4)
                .build())
            .vendor(Vendor.builder()
                .id(2)
                .build())
            .inboundRequestDate(LocalDate.now())
            .inboundExpectDate(LocalDate.now())
            .inboundStatus(InboundStatus.INBOUND_REQUESTED)
            .build();

        Inbound inbound2 = Inbound.builder()
            .member(Member.builder()
                .id(2)
                .build())
            .warehouse(Warehouse.builder()
                .id(5)
                .build())
            .vendor(Vendor.builder()
                .id(3)
                .build())
            .inboundRequestDate(LocalDate.now())
            .inboundExpectDate(LocalDate.now())
            .inboundStatus(InboundStatus.INBOUND_REQUESTED)
            .build();

        // WHEN
        inboundMapper.insert(inbound1);
        inboundMapper.insert(inbound2);

        List<Inbound> approveTestList = List.of(inbound1, inbound2);
        int result = inboundMapper.approveList(approveTestList);

        // THEN
        assertThat(result).isEqualTo(approveTestList.size());
        log.info("승인된 입고 요청 개수: " + result);


    }

    @Test
    void 입고요청_취소() {
    }

    @Test
    void 입고요청_상세조회() {
    }

    @Test
    void 전체_입고요청_개수() {
        // GIVEN
        List<Inbound> inboundList = inboundMapper.selectAllList();

        // THEN
        log.info("입고 조회 : " + inboundList.size());
    }

    @Test
    void insertList() {
    }

    @Test
    void updateList() {
    }

    @Test
    void deleteList() {
    }

    @Test
    void existsByXxx() {
    }
}