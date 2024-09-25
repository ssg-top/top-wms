package com.top.effitopia.controller;

import com.top.effitopia.service.CSCService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

/**
 * 고객센터 Controller
 */
@Controller
@Log4j2
@RequiredArgsConstructor
public class CustomerServiceController {

    private final CSCService cscService;
}
