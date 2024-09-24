package com.top.effitopia.service;

import com.top.effitopia.dto.CheckoutDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;

public interface CheckoutService {
    PageResponseDTO<CheckoutDTO> getList(PageRequestDTO pageRequestDTO);
}
