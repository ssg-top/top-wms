package com.top.effitopia.mapper;

import com.top.effitopia.domain.Contract;

import java.util.List;

public interface ContractMapper {
    int insert();
    List<Contract> selectList();

    List<Integer> updateList();
    int update();
}
