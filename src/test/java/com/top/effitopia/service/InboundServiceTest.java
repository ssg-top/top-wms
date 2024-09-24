package com.top.effitopia.service;

import static org.junit.jupiter.api.Assertions.*;

import com.top.effitopia.mapper.InboundMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class InboundServiceTest {

    @Autowired
    private InboundService inboundService;

    @Mock
    private InboundMapper inboundMapper;

    @Test
    void save() {
    }

    @Test
    void modify() {
    }

    @Test
    void remove() {
    }

    @Test
    void get() {
    }

    @Test
    void getList() {
    }

    @Test
    void saveList() {
    }

    @Test
    void modifyList() {
    }

    @Test
    void removeList() {
    }
}