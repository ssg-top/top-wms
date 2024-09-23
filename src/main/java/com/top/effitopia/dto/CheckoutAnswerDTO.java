package com.top.effitopia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CheckoutAnswerDTO {
    private Integer checkoutAnswerId;
    private CheckoutQuestionDTO checkoutQuestionDTO;
    private CheckoutDTO checkoutDTO;
    private String checkoutAnswerContent;
}
