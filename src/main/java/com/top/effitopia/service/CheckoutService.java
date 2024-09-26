package com.top.effitopia.service;

import com.top.effitopia.dto.*;

import java.util.List;

public interface CheckoutService {
    PageResponseDTO<CheckoutDTO> getList(PageRequestDTO pageRequestDTO);

    boolean save(CheckoutDTO checkoutDTO, List<CheckoutAnswerDTO> checkoutAnswers);

    CheckoutDTO getOne(Integer checkoutId);
}
