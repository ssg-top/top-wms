package com.top.effitopia.service;

import com.top.effitopia.dto.*;

public interface CheckoutService {

    void register(CheckoutDTO checkoutDTO);

    PageResponseDTO<CheckoutDTO> getList(PageRequestDTO pageRequestDTO);

    CheckoutDTO getCheckoutDetail(Integer checkoutId);
}
