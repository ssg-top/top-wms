package com.top.effitopia.mapper;

import com.top.effitopia.domain.AnnualBalance;
import com.top.effitopia.domain.MonthlyBalance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BalanceMapper {

    List<MonthlyBalance> getMonthlyBalances(int year);
    List<AnnualBalance> getAnnualBalances();
}
