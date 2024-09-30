package com.top.effitopia.mapper;

import com.top.effitopia.domain.Revenue;
import com.top.effitopia.dto.BalanceSearchCond;
import com.top.effitopia.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RevenueMapper {

    int insert(Revenue revenue);
    Optional<Revenue> selectOne(int id);
    List<Revenue> selectAll(PageRequestDTO<BalanceSearchCond> pageRequestDTO);
    int selectCount(PageRequestDTO<BalanceSearchCond> pageRequestDTO);
    int update(Revenue revenue);
    int updatePaymentInfo(Revenue revenue);
    int delete(int id);
}
