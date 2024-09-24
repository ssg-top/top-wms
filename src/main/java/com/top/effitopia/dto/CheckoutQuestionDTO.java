package com.top.effitopia.dto;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "상태 체크는 필수 체크 항목입니다!")
    private boolean checkoutStatus;
}
