package com.top.effitopia.service;

import com.top.effitopia.domain.Checkout;
import com.top.effitopia.domain.CheckoutAnswer;
import com.top.effitopia.domain.CheckoutQuestion;
import com.top.effitopia.dto.*;
import com.top.effitopia.enumeration.CheckoutStatus;
import com.top.effitopia.mapper.CheckoutMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutMapper checkoutMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(CheckoutDTO checkoutDTO) {
        Checkout checkout = modelMapper.map(checkoutDTO, Checkout.class);
        checkoutMapper.insertCheckout(checkout);

        List<Integer> questionIds = checkoutMapper.getAllQuestionIds();
        for (Integer questionId : questionIds) {
            CheckoutQuestion question = CheckoutQuestion.builder()
                    .checkoutQuestionId(questionId)
                    .build();

            CheckoutAnswer answer = CheckoutAnswer.builder()
                    .checkout(checkout)
                    .checkoutQuestion(question)
                    .checkoutStatus(CheckoutStatus.PASS)
                    .build();

            checkoutMapper.insertCheckoutAnswer(answer);
        }
    }

    @Override
    public PageResponseDTO<CheckoutDTO> getList(PageRequestDTO pageRequestDTO) {
        List<Checkout> checkoutList = checkoutMapper.selectList(pageRequestDTO);
        List<CheckoutDTO> dtoList = checkoutList.stream()
                .map(checkout -> modelMapper.map(checkout, CheckoutDTO.class))
                .collect(Collectors.toList());
        int total = checkoutMapper.getTotalCount(pageRequestDTO);
        return new PageResponseDTO<>(pageRequestDTO, dtoList, total);
    }

    @Override
    public CheckoutDTO getCheckoutDetail(Integer checkoutId) {
        Checkout checkout = checkoutMapper.selectCheckoutById(checkoutId);
        return modelMapper.map(checkout, CheckoutDTO.class);
    }
}
