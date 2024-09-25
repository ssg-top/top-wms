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

//    @Override
//    public PageResponseDTO<CheckoutDTO> getList(PageRequestDTO pageRequestDTO) {
//        List<Checkout> checkoutList = checkoutMapper.selectList(pageRequestDTO);
//        List<CheckoutDTO> checkoutDTOList = checkoutList.stream().map(vo->modelMapper.map(vo, CheckoutDTO.class)).toList();
//        int total = checkoutMapper.getCount(pageRequestDTO);
//        return PageResponseDTO
//                .<CheckoutDTO>withAll()
//                .dtoList(checkoutDTOList)
//                .total(total)
//                .pageRequestDTO(pageRequestDTO)
//                .build();
//    }

//    @Override
//    @Transactional
//    public boolean save(CheckoutDTO checkoutDTO, CheckoutQuestionDTO checkoutQuestionDTO, CheckoutAnswerDTO checkoutAnswerDTO) {
//        log.info("CheckoutServiceImpl save");
//        Checkout checkout = modelMapper.map(checkoutDTO, Checkout.class);
//        CheckoutQuestion checkoutQuestion = modelMapper.map(checkoutQuestionDTO, CheckoutQuestion.class);
//        CheckoutAnswer checkoutAnswer = modelMapper.map(checkoutAnswerDTO, CheckoutAnswer.class);
//        log.info(checkout);
//        int result = checkoutMapper.insert(checkout, checkoutQuestion, checkoutAnswer);
//        return result >= 3;
//    }

//    @Override
//    @Transactional
//    public boolean save(CheckoutDTO checkoutDTO) {
//        log.info("CheckoutServiceImpl save");
//        Checkout checkout = modelMapper.map(checkoutDTO, Checkout.class);
//        log.info(checkout);
//        int result = checkoutMapper.insert(checkout);
//        return result >= 3;
//    }


    @Override
    @Transactional
    public boolean save(CheckoutDTO checkoutDTO, List<CheckoutAnswerDTO> answers) {
        log.info("CheckoutServiceImpl save");

        Checkout checkout = modelMapper.map(checkoutDTO, Checkout.class);
        int result = checkoutMapper.insertCheckout(checkout);

        for (CheckoutAnswerDTO answerDTO : answers) {
            CheckoutAnswer answer = CheckoutAnswer.builder()
                    .checkout(checkout)
                    .checkoutQuestion(modelMapper.map(answerDTO.getCheckoutQuestionDTO(), CheckoutQuestion.class))
                    .checkoutStatus(answerDTO.getCheckoutStatus())
                    .build();

            checkoutMapper.insertCheckoutAnswer(answer);
        }

        return result > 0;
    }

    @Override
    public List<CheckoutQuestionDTO> getAllQuestions() {
        List<CheckoutQuestion> questions = checkoutMapper.getAllQuestions();
        return questions.stream()
                .map(question -> modelMapper.map(question, CheckoutQuestionDTO.class))
                .collect(Collectors.toList());
    }
}
