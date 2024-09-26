package com.top.effitopia.mapper;

import com.top.effitopia.domain.Checkout;
import com.top.effitopia.domain.CheckoutAnswer;
import com.top.effitopia.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CheckoutMapper {

    int insertCheckout(Checkout checkout);

    int insertCheckoutAnswer(CheckoutAnswer checkoutAnswer);

    List<Checkout> selectList(PageRequestDTO pageRequestDTO);

    Checkout selectCheckoutById(Integer checkoutId);

    int getTotalCount(PageRequestDTO pageRequestDTO);

    List<Integer> getAllQuestionIds();
}
