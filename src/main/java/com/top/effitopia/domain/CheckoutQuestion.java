package com.top.effitopia.domain;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutQuestion {
    private Integer checkoutQuestionId;

    private Checkout checkout;

    private String questionQuestionContent;

    private boolean checkoutStatus;
}
