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
    @Transactional(readOnly = true)
    @Override
    public PageResponseDTO<CheckoutDTO> getList(PageRequestDTO pageRequestDTO) {
        List<CheckoutDTO> checkoutDTOList = checkoutMapper.selectList(pageRequestDTO);
        int total = checkoutMapper.getTotalCount();
        return PageResponseDTO.<CheckoutDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(checkoutDTOList)
                .total(total)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public CheckoutDTO getOne(Long checkoutId) {
        // Checkout VO 가져오기
        Checkout checkout = checkoutMapper.getCheckoutDetails(checkoutId.intValue());

        // VO -> DTO 변환
        return modelMapper.map(checkout, CheckoutDTO.class);
    }

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
//
//
//    @Override
//    @Transactional
//    public boolean save(CheckoutDTO checkoutDTO, List<CheckoutAnswerDTO> answers) {
//        log.info("CheckoutServiceImpl save");
//
//        Checkout checkout = modelMapper.map(checkoutDTO, Checkout.class);
//        int result = checkoutMapper.insertCheckout(checkout);
//
//        for (CheckoutAnswerDTO answerDTO : answers) {
//            CheckoutAnswer answer = CheckoutAnswer.builder()
//                    .checkout(checkout)
//                    .checkoutQuestion(modelMapper.map(answerDTO.getCheckoutQuestionDTO(), CheckoutQuestion.class))
//                    .checkoutStatus(answerDTO.getCheckoutStatus())
//                    .build();
//
//            checkoutMapper.insertCheckoutAnswer(answer);
//        }
//
//        return result > 0;
//    }
//
//    @Override
//    public List<CheckoutQuestionDTO> getAllQuestions() {
//        List<CheckoutQuestion> questions = checkoutMapper.getAllQuestions();
//        return questions.stream()
//                .map(question -> modelMapper.map(question, CheckoutQuestionDTO.class))
//                .collect(Collectors.toList());
//    }
    @Transactional
    @Override
    public boolean save(CheckoutDTO checkoutDTO, List<CheckoutAnswerDTO> checkoutAnswers) {
        Checkout checkout = modelMapper.map(checkoutDTO, Checkout.class);

        int checkoutInsertCount = checkoutMapper.insertCheckout(checkout);

        if (checkoutInsertCount == 0) {
            return false;
        }

        for (CheckoutAnswerDTO answerDTO : checkoutAnswers) {
            CheckoutAnswer answer = CheckoutAnswer.builder()
                    .checkout(checkout)
                    .checkoutQuestion(modelMapper.map(answerDTO.getCheckoutQuestionDTO(), CheckoutQuestion.class))
                    .checkoutStatus(answerDTO.getCheckoutStatus())
                    .build();

            int answerInsertCount = checkoutMapper.insertCheckoutAnswer(answer);

            if (answerInsertCount == 0) {
                return false;
            }
        }

        return true;
    }
}
