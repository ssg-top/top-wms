package com.top.effitopia.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 답변 글 DTO
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerAnswerDTO {

    private int id;                         //답변 ID
    @NotEmpty
    private String AnswerContent;           //답변 내용
    private String AnswerWriter;            //답변 작성자
    private LocalDate regDate;              //답변 작성일
    private LocalDate modDate;              //답변 수정일
    private CustomerInquiryDTO inquiryDTO;  //문의글 정보DTO
}
