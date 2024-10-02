package com.top.effitopia.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.top.effitopia.domain.Inbound;
import com.top.effitopia.domain.Member;
import com.top.effitopia.domain.Product;
import com.top.effitopia.domain.Vendor;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.dto.InboundDTO;
import com.top.effitopia.dto.InboundSearchCond;
import com.top.effitopia.dto.MemberDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.ProductDTO;
import com.top.effitopia.dto.VendorDTO;
import com.top.effitopia.dto.WarehouseDTO;
import com.top.effitopia.enumeration.InboundStatus;
import com.top.effitopia.service.InboundService;
import com.top.effitopia.service.QrService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
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

    @Autowired
    private InboundService inboundService;

    @Autowired
    private QrService qrService;

    @Autowired
    private Validator validator;

    @Test
    void 사업자_입고요청_생성() {
        // Given
        Inbound inbound = Inbound.builder()
            .member(Member.builder().id(3).build())
            .warehouse(Warehouse.builder().id(4).build())
            .vendor(Vendor.builder().id(3).build())
            .product(Product.builder().id(13).build())
            .productQuantity(20)
            .inboundRequestDate(LocalDate.now())
            .inboundExpectDate(LocalDate.of(2024, 10, 4))
            .inboundStatus(InboundStatus.INBOUND_REQUESTED)
            .build();

        // When
        int result = inboundMapper.insert(inbound);

        // Then
        assertThat(result).isEqualTo(1);
        log.info(result);
    }

    @Test
    void 관리자_입고요청_생성() {
        // Given
        Inbound inbound = Inbound.builder()
            .member(Member.builder().id(3).build())
            .warehouse(Warehouse.builder().id(4).build())
            .vendor(Vendor.builder().id(3).build())
            .product(Product.builder().id(5).build())
            .productQuantity(20)
            .inboundRequestDate(LocalDate.now())
            .inboundExpectDate(LocalDate.now())
            .inboundStatus(InboundStatus.INBOUND_REQUESTED)
            .delegateRequesterId(3)
            .build();

        // When
        int result = inboundMapper.insert(inbound);

        // Then
        assertThat(result).isEqualTo(1);
        log.info(result);
    }


    @Test
    void 입고요청_유효성_검증() {
        // Given
        InboundDTO inboundDTO = InboundDTO.builder()
            .memberDTO(MemberDTO.builder().id(3).build())  // DTO 초기화
            .warehouseDTO(WarehouseDTO.builder().id(4).build())
            .vendorDTO(VendorDTO.builder().id(3).build())
            .productDTO(ProductDTO.builder().id(13).build())
            .productQuantity(20)
            .inboundRequestDate(LocalDate.now())
            .inboundExpectDate(LocalDate.of(2024, 10, 3))
            .inboundStatus(InboundStatus.INBOUND_REQUESTED)
            .build();

        // When
        int result = inboundMapper.insert(inboundDTO.toEntity(3, 4));

        // Then
        Set<ConstraintViolation<InboundDTO>> violations = validator.validate(inboundDTO);
        assertFalse(violations.isEmpty());
        assertThat(result).isEqualTo(1);
        log.info(result);
    }


    @Test
    void 입고요청_수정() {
        // Given
        Inbound inbound = Inbound.builder()
            .id(1)
            .productQuantity(10)
            .inboundExpectDate(LocalDate.of(2024, 10, 20))
            .build();

        // When
        int result = inboundMapper.update(inbound);

        // Then
        assertThat(result).isEqualTo(1);
        log.info(result);
    }

    @Test
    void 입고리스트_승인() {
        // Given
        List<Integer> inboundIds = List.of(4, 5);
        int result = inboundMapper.approveList(inboundIds);

        // Then
        assertThat(result).isEqualTo(inboundIds.size());
        log.info("승인된 입고 요청 개수: " + result);
    }

    @Test
    void 입고리스트_완료() {
        // Given
        List<Integer> inboundIds = List.of(2, 5, 9, 10);

        // When
        int result = inboundMapper.completeList(inboundIds);

        // Then
        assertThat(result).isEqualTo(inboundIds.size());
        log.info("완료된 입고 요청 개수: " + result);
    }

    @Test
    void 입고요청_리스트_조회() {
        // Given
        PageRequestDTO<InboundSearchCond> pageRequestDTO = PageRequestDTO.<InboundSearchCond>builder()
            .page(1)
            .size(10)
            .searchCond(new InboundSearchCond())
            .build();

        // When
        List<Inbound> inboundList = inboundMapper.selectAllList(pageRequestDTO);

        // Then
        log.info("조회된 입고 리스트: " + inboundList.size());
        assertThat(inboundList).isNotNull();
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
    void 입고요청_승인_및_QR코드_생성() {
        // Given
        Inbound inbound = Inbound.builder()
            .member(Member.builder()
                .id(3)
                .username("memberName")
                .build())
            .warehouse(Warehouse.builder()
                .id(4)
                .name("warehouseName")
                .build())
            .vendor(Vendor.builder()
                .id(3)
                .name("vendorName")
                .build())
            .product(Product.builder()
                .id(13)
                .name("productName")
                .productBrand("productBrand")
                .build())
            .productQuantity(20)
            .inboundRequestDate(LocalDate.now())
            .inboundExpectDate(LocalDate.of(2024, 10, 1))
            .inboundStatus(InboundStatus.INBOUND_REQUESTED)
            .build();

        inboundMapper.insert(inbound);

        // When
        List<Integer> inboundIds = List.of(inbound.getId());
        boolean success = inboundService.approveInboundRequests(inboundIds);

        // Then
        assertThat(success).isTrue();
        assertThat(qrService.get(inbound.getId()).isPresent()).isTrue();
        log.info("QR 코드가 생성되었습니다: " + qrService.get(inbound.getId()));
    }
}
