package com.top.effitopia.mapper;

import com.top.effitopia.domain.*;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.WarehouseDTO;
import com.top.effitopia.enumeration.InboundStatus;
import com.top.effitopia.enumeration.MemberRole;
import com.top.effitopia.enumeration.MemberStatus;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class WarehouseMapperTest {

    @Autowired
    private WarehouseMapper warehouseMapper;

    Warehouse warehouse;
    Member member;
    Address address;
    WarehouseType warehouseType;

    @Test
    public void selectIdTest(){
        warehouse = warehouseMapper.selectId(1);
        log.info(warehouse);
    }

    @Test
    public void selectNameTest(){
        String name = warehouseMapper.selectName("신사 냉동 창고");
        log.info(name);
    }

    @Test
    public void selectWarehouseListTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().keyword("충북").page(1).size(10).build();

        List<Warehouse> warehouseList = warehouseMapper.selectWarehouseList(pageRequestDTO);

        log.info(warehouseList);
    }

    @Test
    public void selectCellListTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().searchCond(6).page(1).size(10).build();

        List<Cell> cellList = warehouseMapper.selectCellList(pageRequestDTO);

        log.info(cellList);
    }

    @Test
    public void updateTest(){
        member = Member.builder()
                .username("manager" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("name")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.WAREHOUSE_MANAGER)
                .address(Address.builder().zipCode("zip").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();

        address = Address.builder().zipCode("a").roadNameAddress("b").lotNumberAddress("c").detailAddress("d").build();

        warehouse = Warehouse.builder().
                id(1).
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
        int result = warehouseMapper.update(warehouse);
        log.info(result);
    }

    @Test
    public void getCountTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        int num = warehouseMapper.getCount(pageRequestDTO);
        log.info(num);
    }

    @Test
    public void getTypeListTest(){
        List<WarehouseType> typeList = warehouseMapper.selectAllType();
        log.info(typeList);
    }

    @Test
    public void selectUserTest(){
        List<Member> memberList = warehouseMapper.selectAssignableWarehouseManagerList();
        log.info(memberList);
    }
}
