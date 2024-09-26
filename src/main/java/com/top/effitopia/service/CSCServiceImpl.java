package com.top.effitopia.service;

import com.top.effitopia.domain.CustomerAnswer;
import com.top.effitopia.domain.CustomerInquiry;
import com.top.effitopia.dto.CustomerAnswerDTO;
import com.top.effitopia.dto.CustomerInquiryDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.mapper.CSCMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 고객센터 Service Impl
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class CSCServiceImpl implements CSCService{

    private final ModelMapper modelMapper;
    private final CSCMapper cscMapper;
    @Override
    public void saveInquiry(CustomerInquiryDTO customerInquiryDTO) {
        log.info("saveInquiry Service..............");
        log.info(customerInquiryDTO.toString());
        CustomerInquiry customerInquiry = modelMapper.map(customerInquiryDTO, CustomerInquiry.class);
        log.info(customerInquiry.toString());
        cscMapper.insertInquiry(customerInquiry);

    }

    @Override
    public void modifyInquiry(CustomerInquiryDTO customerInquiryDTO) {
        log.info("modifyInquiry Service..............");
        log.info(customerInquiryDTO.toString());
        CustomerInquiry customerInquiry = modelMapper.map(customerInquiryDTO, CustomerInquiry.class);
        log.info(customerInquiry.toString());
        cscMapper.updateInquiry(customerInquiry);
    }

    @Override
    public void removeInquiry(int no) {
        log.info("removeInquiry Service..............");
        cscMapper.deleteAnswerToInquiryId(no); //문의글 자식으로 등록된 답변 먼저 삭제
        cscMapper.deleteInquiry(no);
    }

    @Override
    public CustomerInquiryDTO getInquiry(int no)
    {
        log.info("getInquiry Service..............");
        Optional<CustomerInquiry> customerInquiry = cscMapper.selectOneInquiry(no);
        CustomerInquiryDTO customerInquiryDTO;
        if(customerInquiry.isPresent()) {
            customerInquiryDTO = modelMapper.map(customerInquiry.get(), CustomerInquiryDTO.class);
            return customerInquiryDTO;
        }
        else {
            return null;
        }
    }

    @Override
    public PageResponseDTO<CustomerInquiryDTO> getListInquiry(PageRequestDTO pageRequestDTO) {
        log.info("getPageListInquiry Service..............");
        return null;
    }

    @Override
    public void saveAnswer(CustomerAnswerDTO customerAnswerDTO) {
        log.info("saveAnswer Service..............");
        CustomerAnswer customerAnswer = modelMapper.map(customerAnswerDTO, CustomerAnswer.class);
        cscMapper.insertAnswer(customerAnswer);

    }

    @Override
    public CustomerAnswerDTO getAnswer(int no) {
        log.info("getAnswer Service..............");
        Optional<CustomerAnswer> customerAnswer = cscMapper.selectOneAnswer(no);
        CustomerAnswerDTO customerAnswerDTO;
        if(customerAnswer.isPresent()) {
            customerAnswerDTO = modelMapper.map(customerAnswer.get(), CustomerAnswerDTO.class);
            return  customerAnswerDTO;
        }
        return null;
    }

    @Override
    public void modifyAnswer(CustomerAnswerDTO customerAnswerDTO) {
        log.info("modifyAnswer Service..............");
        CustomerAnswer customerAnswer = modelMapper.map(customerAnswerDTO, CustomerAnswer.class);
        cscMapper.updateAnswer(customerAnswer);
    }

    @Override
    public void removeAnswer(int no) {
        log.info("removeAnswer Service..............");
        cscMapper.deleteAnswer(no);
    }
}
