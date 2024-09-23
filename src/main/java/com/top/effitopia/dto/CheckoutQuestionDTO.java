package com.top.effitopia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CheckoutQuestionDTO {
    private Integer checkoutQuestionId;
    private String questionQuestionContent;
    private boolean checkoutStatus;
}
