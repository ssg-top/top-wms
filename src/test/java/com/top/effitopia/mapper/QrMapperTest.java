package com.top.effitopia.mapper;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Transactional
@SpringBootTest
class QrMapperTest {

    @Autowired
    private QrMapper qrMapper;


    @Test
    void QR_생성() {
    }

    @Test
    void QR_조회() {
    }
}