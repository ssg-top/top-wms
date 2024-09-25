package com.top.effitopia.dto;

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

    private CheckoutQuestionDTO checkoutQuestionDTO;

    @NotEmpty(message = "추후 조치사항은 비어 있을 수 없습니다!")
    private String checkoutAnswerContent;
}
