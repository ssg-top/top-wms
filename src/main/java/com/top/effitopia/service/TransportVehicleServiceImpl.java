package com.top.effitopia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class TransportVehicleServiceImpl implements TransportVehicleService {
    private final ModelMapper modelMapper;
}
