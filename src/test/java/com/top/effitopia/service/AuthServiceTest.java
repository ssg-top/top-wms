package com.top.effitopia.service;

import com.top.effitopia.enumeration.MemberRole;
import com.top.effitopia.dto.JoinDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class AuthServiceTest {

    @Autowired AuthService authService;

    @Test
    void join() {
        int randInt = (int) (Math.random() * 10000);
        JoinDTO joinDTO = JoinDTO.builder()
                .username("test" + randInt)
                .password("password")
                .name("name")
                .phone("01012345678")
                .email("example" + randInt + "@gmail.com")
                .role(MemberRole.BUSINESS_PROPRIETOR)
                .zipCode("")
                .roadNameAddress("")
                .lotNumberAddress("")
                .detailAddress("")
                .businessNumber("1234567890")
                .build();
        Assertions.assertTrue(authService.join(joinDTO));
    }
}
