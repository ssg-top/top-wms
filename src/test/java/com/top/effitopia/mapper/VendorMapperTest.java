package com.top.effitopia.mapper;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.top.effitopia.domain.Vendor;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@SpringBootTest
class VendorMapperTest {

    @Autowired
    private VendorMapper vendorMapper;

    @Test
    void 거래처_생성() {
        // Given
        Vendor vendor = Vendor.builder()
            .name("파하")
            .build();

        // When
        int result = vendorMapper.insert(vendor);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void 모든_거래처_조회() {
        List<Vendor> vendors = vendorMapper.selectAllList();
        log.info("Vendor Test:  " + vendors.size());
    }

    @Test
    void 거래처_업데이트() {
        // Given
        Vendor vendor = Vendor.builder()
            .id(1)
            .name("아자차")
            .build();

        // When
        int result = vendorMapper.update(vendor);

        // Then
        assertThat(result).isEqualTo(1);
        log.info(result);

    }

    @Test
    void 거래처_삭제() {
        // Given
        Vendor vendor =Vendor.builder()
            .id(2)
            .delFlag(false)
            .build();

        // When
        int result = vendorMapper.delete(vendor.getId());

        // Then
        assertThat(result).isEqualTo(1);
        log.info(result);
    }


    @Test
    void deleteList() {
    }
}