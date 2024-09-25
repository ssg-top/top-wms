package com.top.effitopia.domain;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutQuestion {
    private Integer checkoutQuestionId;

    private String questionQuestionContent;
}
