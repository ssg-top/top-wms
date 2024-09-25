package com.top.effitopia.dto;

import com.top.effitopia.enumeration.CheckoutStatus;
import jakarta.validation.constraints.NotEmpty;
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

    private CheckoutDTO checkoutDTO;

    private CheckoutQuestionDTO checkoutQuestionDTO;

    private CheckoutStatus checkoutStatus;
}
