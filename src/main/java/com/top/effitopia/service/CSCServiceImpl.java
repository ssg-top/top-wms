package com.top.effitopia.service;

import com.top.effitopia.dto.CustomerAnswerDTO;
import com.top.effitopia.dto.CustomerInquiryDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.mapper.CSCMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    }

    @Override
    public void modifyInquiry(CustomerInquiryDTO customerInquiryDTO) {

    }

    @Override
    public void removeInquiry(int no) {

    }

    @Override
    public CustomerInquiryDTO getInquiry(int no) {
        return null;
    }

    @Override
    public PageResponseDTO<CustomerInquiryDTO> getListInquiry(PageRequestDTO pageRequestDTO) {
        return null;
    }

    @Override
    public void saveAnswer(CustomerAnswerDTO customerAnswerDTO) {

    }

    @Override
    public CustomerAnswerDTO getAnswer(int no) {
        return null;
    }

    @Override
    public void modifyAnswer(CustomerAnswerDTO customerAnswerDTO) {

    }

    @Override
    public void removeAnswer(int no) {

    }
}
