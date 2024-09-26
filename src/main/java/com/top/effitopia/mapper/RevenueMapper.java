package com.top.effitopia.mapper;

import com.top.effitopia.domain.Revenue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RevenueMapper {

    int insert(Revenue revenue);
    Optional<Revenue> selectOne(int id);
    List<Revenue> selectAll();
    int update(Revenue revenue);
    int delete(int id);
}
