package com.top.effitopia.controller;

import com.top.effitopia.service.StockCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

@Controller
@Log4j2
@RequiredArgsConstructor
public class StockCheckController {

    private final StockCheckService stockCheckService;
}
