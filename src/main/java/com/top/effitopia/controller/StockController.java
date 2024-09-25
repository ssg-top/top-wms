package com.top.effitopia.controller;

import com.top.effitopia.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

/**
 * 재고 Controller
 */
@Controller
@Log4j2
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
}
