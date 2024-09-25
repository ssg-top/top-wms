package com.top.effitopia.controller;

import com.top.effitopia.service.StockCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

/**
 * 재고실사 Controller
 */
@Controller
@Log4j2
@RequiredArgsConstructor
public class StockCheckController {

    private final StockCheckService stockCheckService;
}
