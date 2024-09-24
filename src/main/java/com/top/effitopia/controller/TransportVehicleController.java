package com.top.effitopia.controller;

import com.top.effitopia.service.TransportVehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vehicles")
@Log4j2
@RequiredArgsConstructor
public class TransportVehicleController {
    private final TransportVehicleService transportVehicleService;
}
