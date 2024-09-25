package com.top.effitopia.mapper;

import com.top.effitopia.domain.Checkout;
import com.top.effitopia.domain.CheckoutAnswer;
import com.top.effitopia.domain.CheckoutQuestion;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.enumeration.CheckoutStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class CheckoutMapperTest {

    @Autowired
    private CheckoutMapper checkoutMapper;

    @Test
    void testInsertCheckout() {
        Warehouse warehouse = Warehouse.builder()
                .id(1L)
                .build();

        Checkout checkout = Checkout.builder()
//                .warehouse(null)
//                .warehouse(Warehouse.builder().build())
                .warehouse(warehouse)
                .checkoutComment("Test comment")
                .regDate(LocalDateTime.now())
                .build();

        int result = checkoutMapper.insertCheckout(checkout);

        assertEquals(1, result);
        assertNotNull(checkout.getCheckoutId());
    }

    @Test
    void testInsertCheckoutAnswer() {
        Checkout checkout = Checkout.builder()
                .checkoutId(1)
                .build();

        CheckoutQuestion checkoutQuestion = CheckoutQuestion.builder()
                .checkoutQuestionId(1)
                .build();

        CheckoutAnswer answer = CheckoutAnswer.builder()
                .checkout(checkout)
                .checkoutQuestion(checkoutQuestion)
                .checkoutStatus(CheckoutStatus.PASS)
                .build();

        int result = checkoutMapper.insertCheckoutAnswer(answer);
        assertEquals(1, result);
    }

//    @Test
//    void testSelectAll(){
//        String test = checkoutMapper.selectAll(1);
//        System.out.println(test);
//    }
}
