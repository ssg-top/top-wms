package com.top.effitopia.mapper;

import com.top.effitopia.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    void insertOrder(Order order);

    void updateOrder(Order order);
}
