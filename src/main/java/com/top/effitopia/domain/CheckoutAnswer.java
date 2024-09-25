package com.top.effitopia.domain;

import com.top.effitopia.enumeration.CheckoutStatus;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutAnswer {
    private Integer checkoutAnswerId;

    private Checkout checkout;

    private CheckoutQuestion checkoutQuestion;

    private CheckoutStatus checkoutStatus;
}
