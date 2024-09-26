package com.top.effitopia.mapper;

import com.top.effitopia.domain.*;
import com.top.effitopia.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class ContractMapperTest {

    @Autowired
    private ContractMapper contractMapper;
    Warehouse warehouse;
    Member member;
    Address address;

    @Test
    public void selectListAllTest(){
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().size(10).page(1).build();
//
//        List<Contract> contractList = contractMapper.selectListAll(pageRequestDTO);
//
//        log.info(contractList);
    }

    @Test
    public void selectListByStatusTest(){
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().size(10).page(1).build();
//
//        List<Contract> contractStatusList = contractMapper.selectListByStatus(1,pageRequestDTO);
//
//        log.info(contractStatusList);
    }
}
