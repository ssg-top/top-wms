package com.top.effitopia.mapper;

import com.top.effitopia.domain.*;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.enumeration.ContractStatus;
import com.top.effitopia.enumeration.MemberRole;
import com.top.effitopia.enumeration.MemberStatus;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ContractMapperTest {

    @Autowired
    private ContractMapper contractMapper;

    WarehouseType warehouseType;
    Warehouse warehouse;
    Member member;
    Address address;

    @Test
    public void selectListAllTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().size(10).page(1).build();

        List<Contract> contractList = contractMapper.selectListAll(pageRequestDTO);

        log.info(contractList);
    }

    @Test
    public void selectListByStatusTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().searchCond(ContractStatus.APPROVE).build();

        List<Contract> contractStatusList = contractMapper.selectListByStatus(pageRequestDTO);

        log.info(contractStatusList);
    }

    @Test
    public void insertTest(){
        warehouseType = WarehouseType.builder().id(1).type("냉동").build();

        address = Address.builder().zipCode("a").roadNameAddress("b").lotNumberAddress("c").detailAddress("d").build();

        member = Member.builder()
                .id(1)
                .username("manager" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("name")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.WAREHOUSE_MANAGER)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();

        warehouse = Warehouse.builder().
                id(2).
                member(member).
                warehouseType(warehouseType).
                code("code").
                name("name").
                phone("010-1234-5678").
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

        String dateString = "2024-09-28 15:30:45";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

        Contract contract = Contract.builder().
                            warehouse(warehouse).
                            member(member).
                            startDate(dateTime).
                            status(ContractStatus.APPROVE).
                            date(10).
                            regDate(LocalDateTime.now()).build();

        int result = contractMapper.insert(contract);

        log.info(result);
    }

    @Test
    public void updateTest(){
        warehouseType = WarehouseType.builder().id(1).type("냉동").build();

        address = Address.builder().zipCode("a").roadNameAddress("b").lotNumberAddress("c").detailAddress("d").build();

        member = Member.builder()
                .id(1)
                .username("manager" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("name")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.WAREHOUSE_MANAGER)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();

        warehouse = Warehouse.builder().
                id(2).
                member(member).
                warehouseType(warehouseType).
                code("code").
                name("name").
                phone("010-1234-5678").
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

        Contract contract = Contract.builder().
                id(4).
                warehouse(warehouse).
                member(member).
                status(ContractStatus.REJECT).
                build();

        contractMapper.update(contract);
    }

    @Test
    public void updateApprovalListTest(){
        List<Contract> contractList = new ArrayList<>();

        contractList.add(Contract.builder().id(3).status(ContractStatus.REQUEST).build());
        contractList.add(Contract.builder().id(4).status(ContractStatus.REQUEST).build());
        contractList.add(Contract.builder().id(5).status(ContractStatus.REQUEST).build());

        contractMapper.updateApprovalList(contractList);
    }

    @Test
    public void updateRejectListTest(){
        List<Contract> contractList = new ArrayList<>();

        contractList.add(Contract.builder().id(3).status(ContractStatus.REQUEST).build());
        contractList.add(Contract.builder().id(4).status(ContractStatus.REQUEST).build());
        contractList.add(Contract.builder().id(5).status(ContractStatus.REQUEST).build());

        contractMapper.updateRejectList(contractList);
    }

    @Test
    public void getOneTest(){
        WarehouseCost warehouseCost = contractMapper.getOne(1);
        log.info(warehouseCost);
    }
}
