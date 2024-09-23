package com.top.effitopia.mapper;

import com.top.effitopia.domain.Checkout;

import java.util.List;
import java.util.Optional;

public interface CheckoutMapper {
    int insert(Checkout checkout);
    List<Checkout> selectList();
    Optional<Checkout> selectOne(int id);
}
