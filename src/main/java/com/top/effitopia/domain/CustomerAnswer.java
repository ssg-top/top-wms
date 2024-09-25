package com.top.effitopia.domain;

import lombok.*;

import java.time.LocalDate;

/**
 * 답변 글
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAnswer {
    private int	AnswerID;//답변 ID
    private String AnswerContent;//답변 내용
    private String AnswerWriter;//답변 작성자
    private LocalDate regDate;//답변 작성일
    private LocalDate modDate;//답변 수정일
    private CustomerInquiry	inquiry;//문의글 정보
}
