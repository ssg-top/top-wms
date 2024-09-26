package com.top.effitopia.mapper;

import com.top.effitopia.domain.Checkout;
import com.top.effitopia.domain.CheckoutAnswer;
import com.top.effitopia.domain.CheckoutQuestion;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.enumeration.CheckoutStatus;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
@Log4j2
public class CheckoutMapperTest {

    @Autowired
    private CheckoutMapper checkoutMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testInsertCheckout() {
        Checkout checkout = Checkout.builder()
                .warehouse(Warehouse.builder().id(1).build())
                .checkoutContent("테스트 체크아웃")
                .build();

        int result = checkoutMapper.insertCheckout(checkout);

        assertEquals(result, 1);
    }

    @Test
    public void testInsertCheckoutAnswer() {
        Checkout checkout = Checkout.builder()
                .checkoutId(2)
                .build();

        List<Integer> questionIds = checkoutMapper.getAllQuestionIds();
        for (Integer questionId : questionIds) {
            CheckoutAnswer answer = CheckoutAnswer.builder()
                    .checkout(checkout)
                    .checkoutQuestion(CheckoutQuestion.builder().checkoutQuestionId(questionId).build())
                    .checkoutStatus(CheckoutStatus.PASS)
                    .build();
            int result = checkoutMapper.insertCheckoutAnswer(answer);

            assertEquals(result, 1);
        }
    }

    @Test
    public void testSelectList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("테스트")
                .build();

        List<Checkout> checkoutList = checkoutMapper.selectList(pageRequestDTO);

        assertThat(checkoutList).isNotNull();
    }

    @Test
    public void testSelectCheckoutById() {
        Checkout checkout = checkoutMapper.selectCheckoutById(2); // 실제 존재하는 checkoutId 사용

        assertThat(checkout).isNotNull();
        assertThat(checkout.getCheckoutId()).isEqualTo(2); // 실제로 조회한 ID와 일치하는지 확인
    }

    @Test
    public void testGetTotalCount() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .keyword("테스트")
                .build();

        int totalCount = checkoutMapper.getTotalCount(pageRequestDTO);

        assertThat(totalCount).isGreaterThan(0);
    }

    @Test
    public void testGetAllQuestionIds() {
        List<Integer> questionIds = checkoutMapper.getAllQuestionIds();

        assertThat(questionIds).isNotNull();
    }
}
