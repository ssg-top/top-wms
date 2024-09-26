package com.top.effitopia.mapper;

import com.top.effitopia.domain.Contract;
import com.top.effitopia.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractMapper {
    int insert();
    List<Contract> selectListAll(PageRequestDTO pageRequestDTO);
    List<Contract> selectListByStatus(Integer id, PageRequestDTO pageRequestDTO);
    List<Integer> updateList();
    int update();
}
