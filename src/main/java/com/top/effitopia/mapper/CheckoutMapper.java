package com.top.effitopia.mapper;

import com.top.effitopia.domain.Checkout;
import com.top.effitopia.domain.CheckoutAnswer;
import com.top.effitopia.domain.CheckoutQuestion;
import com.top.effitopia.dto.CheckoutDTO;
import com.top.effitopia.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CheckoutMapper {
    List<Checkout> selectList(PageRequestDTO pageRequestDTO);

    int getTotalCount(PageRequestDTO pageRequestDTO);

    int insertCheckout(Checkout checkout);

    int insertCheckoutAnswer(CheckoutAnswer checkoutAnswer);

    Checkout getCheckoutDetails(Integer checkoutId);
}
