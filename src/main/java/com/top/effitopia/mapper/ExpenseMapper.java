package com.top.effitopia.mapper;

import com.top.effitopia.domain.Expense;
import com.top.effitopia.dto.BalanceSearchCond;
import com.top.effitopia.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ExpenseMapper {

    int insert(Expense expense);
    Optional<Expense> selectOne(int id);
    List<Expense> selectAll(PageRequestDTO<BalanceSearchCond> pageRequestDTO);
    int selectCount(PageRequestDTO<BalanceSearchCond> pageRequestDTO);
    int update(Expense expense);
    int delete(int id);
}
