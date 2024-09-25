package com.top.effitopia.mapper;

import com.top.effitopia.domain.Contract;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractMapper {
    int insert();
    List<Contract> selectList();

    List<Integer> updateList();
    int update();
}
