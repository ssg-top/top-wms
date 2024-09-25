package com.top.effitopia.domain;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutAnswer {
    private Integer checkoutAnswerId;

    private CheckoutQuestion checkoutQuestion;

    private String checkoutAnswerContent;
}
