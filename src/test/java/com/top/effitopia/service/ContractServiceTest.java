package com.top.effitopia.service;

import com.top.effitopia.domain.*;
import com.top.effitopia.dto.*;
import com.top.effitopia.enumeration.ContractStatus;
import com.top.effitopia.enumeration.MemberRole;
import com.top.effitopia.enumeration.MemberStatus;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ContractServiceTest {

    @Autowired
    private ContractService contractService;

    WarehouseDTO WarehouseDTO;

    MemberDTO memberDTO;

    Address address;

    WarehouseType warehouseType;

    @Test
    public void modifyTest(){
        WarehouseTypeDTO warehouseTypeDTO = WarehouseTypeDTO.builder().id(1).type("냉동").build();
        address = Address.builder().zipCode("a").roadNameAddress("b").lotNumberAddress("c").detailAddress("d").build();

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



        WarehouseDTO = WarehouseDTO.builder().
                id(1).
                memberDTO(memberDTO).
                warehouseType(warehouseTypeDTO).
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

        ContractDTO contractDTO = ContractDTO.builder().id(1)
                .warehouseDTO(WarehouseDTO)
                .memberDTO(memberDTO)
                .startDate(LocalDateTime.now())
                .date(10)
                .endDate(LocalDateTime.now().plusMonths(10))
                .status(ContractStatus.APPROVE)
                .regDate(LocalDateTime.now())
                .build();

        boolean result = contractService.modify(contractDTO);
        log.info(result);
    }

    @Test
    public void getContractListTest(){

    }

    @Test
    public void saveTest(){
        WarehouseTypeDTO warehouseType = WarehouseTypeDTO.builder().id(1).type("냉동").build();
        address = Address.builder().zipCode("a").roadNameAddress("b").lotNumberAddress("c").detailAddress("d").build();

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

        WarehouseDTO = WarehouseDTO.builder().
                id(1).
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

        ContractDTO contractDTO = ContractDTO.builder()
                .warehouseDTO(WarehouseDTO)
                .memberDTO(memberDTO)
                .startDate(LocalDateTime.now())
                .date(10)
                .endDate(LocalDateTime.now().plusMonths(10))
                .status(ContractStatus.APPROVE)
                .regDate(LocalDateTime.now())
                .build();

        boolean result = contractService.save(contractDTO);
        log.info(result);
    }

    @Test
    public void approvalModifyTest(){

    }

    @Test
    public void getOneTest(){
        WarehouseCostDTO warehouseCostDTO = contractService.get(10);
        log.info(warehouseCostDTO);
    }
}
