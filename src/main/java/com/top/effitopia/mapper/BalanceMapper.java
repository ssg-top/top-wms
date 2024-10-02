package com.top.effitopia.mapper;

import com.top.effitopia.domain.AnnualBalance;
import com.top.effitopia.domain.MonthlyBalance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BalanceMapper {

    Optional<AnnualBalance> getAnnualBalance(int year);
    List<MonthlyBalance> getMonthlyBalances(int year);

    List<AnnualBalance> getAnnualBalances();
}
