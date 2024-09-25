package com.top.effitopia.domain;

import lombok.*;

import java.time.LocalDate;

/**
 * 문의 글
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerInquiry {
    private int	inquiryID;          //문의글 ID
    private String	inquiryTitle;   //문의글 제목
    private String	inquiryContent; //문의글 내용
    private String	inquiryWriter;  //문의글 작성자
    private Boolean	inquiryState;   //문의글 답변 상태
    private LocalDate regDate;      //문의글 작성일
    private LocalDate modDate;      //문의글 수정일
    private Member member;          //회원 정보
}
