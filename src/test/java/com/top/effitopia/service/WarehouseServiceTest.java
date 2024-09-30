package com.top.effitopia.service;

import ch.qos.logback.core.net.SMTPAppenderBase;
import com.top.effitopia.domain.Address;
import com.top.effitopia.domain.Member;
import com.top.effitopia.domain.WarehouseType;
import com.top.effitopia.dto.*;
import com.top.effitopia.enumeration.MemberRole;
import com.top.effitopia.enumeration.MemberStatus;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class WarehouseServiceTest {

    @Autowired
    private WarehouseService warehouseService;

    WarehouseDTO warehouseDTO;

    MemberDTO memberDTO;

    Address address;

    WarehouseType warehouseType;

    @Test
    public void getWarehouseListTest(){

    }

    @Test
    public void getCellListTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().searchCond(1).page(1).size(10).build();
        PageResponseDTO<CellDTO> responseDTO = warehouseService.getCellList(pageRequestDTO);
        responseDTO.getDtoList().forEach(log::info);

    }

    @Test
    public void getIdTest(){

    }

    @Test
    public void getNameTest(){
        Optional<String> name = warehouseService.get("판교 냉장 창고");
        log.info(name);
    }

    @Test
    public void getModifyTest(){
        warehouseType = WarehouseType.builder().id(1).type("냉동").build();

        memberDTO = memberDTO.builder()
                .id(2)
                .username("manager" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("name")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.WAREHOUSE_MANAGER)
                .zipCode("")
                .roadNameAddress("")
                .lotNumberAddress("")
                .build();

        address = Address.builder().zipCode("a").roadNameAddress("b").lotNumberAddress("c").detailAddress("d").build();

        warehouseDTO = WarehouseDTO.builder().
                id(35).
                memberDTO(memberDTO).
                warehouseType(warehouseType).
                code("ccccccc").
                name("nnnnnn").
                phone("437249327437294").
                zipCode(address.getZipCode()).
                roadName(address.getRoadNameAddress()).
                lotNumber(address.getLotNumberAddress()).
                detailAddress(address.getDetailAddress()).
                width(1).
                length(2).
                height(3).
                capacity(4).
                latitude(0.00001).
                longitude(0.00002).
                regDate(LocalDateTime.now()).build();

        boolean result = warehouseService.modify(warehouseDTO);
        log.info(result);
    }

    @Test
    public void saveTest(){
        warehouseType = WarehouseType.builder().id(1).type("냉동").build();

        memberDTO = memberDTO.builder()
                .id(2)
                .username("manager" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("name")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.WAREHOUSE_MANAGER)
                .zipCode("")
                .roadNameAddress("")
                .lotNumberAddress("")
                .build();

        address = Address.builder().zipCode("a").roadNameAddress("b").lotNumberAddress("c").detailAddress("d").build();

        warehouseDTO = WarehouseDTO.builder().
                memberDTO(memberDTO).
                warehouseType(warehouseType).
                code("ccccccc").
                name("nnnnnn").
                phone("437249327437294").
                zipCode(address.getZipCode()).
                roadName(address.getRoadNameAddress()).
                lotNumber(address.getLotNumberAddress()).
                detailAddress(address.getDetailAddress()).
                width(1).
                length(2).
                height(3).
                capacity(4).
                latitude(0.00001).
                longitude(0.00002).
                regDate(LocalDateTime.now()).build();

        warehouseService.save(warehouseDTO);
    }

    @Test
    public void getTypeListTest(){
        List<WarehouseTypeDTO> dtoList = warehouseService.getTypeList();
        log.info(dtoList);
    }
}
