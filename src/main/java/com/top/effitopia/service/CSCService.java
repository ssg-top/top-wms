package com.top.effitopia.service;

import com.top.effitopia.dto.CustomerAnswerDTO;
import com.top.effitopia.dto.CustomerInquiryDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;

/**
 * 고객센터 Sevice interface
 */
public interface CSCService {

    public void saveInquiry(CustomerInquiryDTO customerInquiryDTO);
    public void modifyInquiry(CustomerInquiryDTO customerInquiryDTO);
    public void removeInquiry(int no);
    public CustomerInquiryDTO getInquiry(int no);
    PageResponseDTO<CustomerInquiryDTO> getListInquiry(PageRequestDTO pageRequestDTO);



    public void saveAnswer(CustomerAnswerDTO customerAnswerDTO);
    public CustomerAnswerDTO getAnswer(int no);
    public void modifyAnswer(CustomerAnswerDTO customerAnswerDTO);
    public void removeAnswer(int no);
}
