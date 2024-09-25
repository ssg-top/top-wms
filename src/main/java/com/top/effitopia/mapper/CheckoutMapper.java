package com.top.effitopia.mapper;

import com.top.effitopia.domain.Checkout;
import com.top.effitopia.domain.CheckoutAnswer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckoutMapper {
//    Integer insert(Checkout checkout, CheckoutQuestion checkoutQuestion, CheckoutAnswer checkoutAnswer);
//    Integer insert(Checkout checkout);
//    List<Checkout> selectList(PageRequestDTO pageRequestDTO);
//    Optional<Checkout> selectOne(int id);
//    int getCount(PageRequestDTO pageRequestDTO);
//
//    Integer insertCheckout(Checkout checkout);
//
//    Integer insertCheckoutAnswer(CheckoutAnswer checkoutAnswer);
//
//    List<CheckoutQuestion> getAllQuestions();

    int insertCheckout(Checkout checkout);

    int insertCheckoutAnswer(CheckoutAnswer checkoutAnswer);

//    String selectAll(int id);
}
