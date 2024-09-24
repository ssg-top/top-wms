package com.top.effitopia.mapper;

import com.top.effitopia.domain.Checkout;
import com.top.effitopia.dto.PageRequestDTO;
import jakarta.validation.constraints.Max;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CheckoutMapper {
    Integer insert(Checkout checkout);
    List<Checkout> selectList(PageRequestDTO pageRequestDTO);
    Optional<Checkout> selectOne(int id);
    int getCount(PageRequestDTO pageRequestDTO);
}
