package com.top.effitopia.service;

import com.top.effitopia.domain.Checkout;
import com.top.effitopia.domain.CheckoutAnswer;
import com.top.effitopia.domain.CheckoutQuestion;
import com.top.effitopia.dto.*;
import com.top.effitopia.mapper.CheckoutMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutMapper checkoutMapper;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<CheckoutDTO> getList(PageRequestDTO pageRequestDTO) {
        List<Checkout> voList = checkoutMapper.selectList(pageRequestDTO);
        List<CheckoutDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, CheckoutDTO.class))
                .collect(Collectors.toList());
        int total = checkoutMapper.getTotalCount(pageRequestDTO);
        return PageResponseDTO.<CheckoutDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public CheckoutDTO getOne(Integer checkoutId) {
        Checkout checkout = checkoutMapper.getCheckoutDetails(checkoutId);
        return modelMapper.map(checkout, CheckoutDTO.class);
    }

    @Transactional
    @Override
    public boolean save(CheckoutDTO checkoutDTO, List<CheckoutAnswerDTO> checkoutAnswers) {
        Checkout checkout = modelMapper.map(checkoutDTO, Checkout.class);
        int result = checkoutMapper.insertCheckout(checkout);
        if (result == 0) {
            return false;
        }

        List<CheckoutAnswer> answers = checkoutAnswers.stream()
                .map(answerDTO -> {
                    CheckoutQuestion question = modelMapper.map(answerDTO.getCheckoutQuestionDTO(), CheckoutQuestion.class);

                    return CheckoutAnswer.builder()
                            .checkout(checkout)
                            .checkoutQuestion(question)
                            .checkoutStatus(answerDTO.getCheckoutStatus())
                            .build();
                }).toList();

        for (CheckoutAnswer answer : answers) {
            int answerResult = checkoutMapper.insertCheckoutAnswer(answer);
            if (answerResult == 0) {
                return false;
            }
        }
        return true;
    }
}
