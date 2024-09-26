package com.top.effitopia.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 문의 글 DTO
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerInquiryDTO {
    private int id;                 //문의글 ID
    @NotEmpty
    private String inquiryTitle;    //문의글 제목
    @NotEmpty
    private String inquiryContent;  //문의글 내용
    private String inquiryWriter;   //문의글 작성자
    private Boolean inquiryState;   //문의글 답변 상태
    private LocalDate regDate;      //문의글 작성일
    private LocalDate modDate;      //문의글 수정일
    private MemberDTO memberDTO;    //회원 정보
}
