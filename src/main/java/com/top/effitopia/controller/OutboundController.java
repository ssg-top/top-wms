package com.top.effitopia.controller;

import com.top.effitopia.service.OutboundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/outbounds")
@Log4j2
@RequiredArgsConstructor
public class OutboundController {
    private final OutboundService outboundService;
}
