package com.top.effitopia.service;

import com.top.effitopia.domain.Address;
import com.top.effitopia.domain.Member;
import com.top.effitopia.enumeration.MemberRole;
import com.top.effitopia.dto.JoinDTO;
import com.top.effitopia.enumeration.MemberStatus;
import com.top.effitopia.exception.BizException;
import com.top.effitopia.mapper.MemberMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Log4j2
@SpringBootTest
class AuthServiceTest {

    @Autowired
    AuthServiceImpl authServiceImpl;

    @Autowired
    MemberMapper memberMapper;

    @Test
    void save() {
        int randInt = (int) (Math.random() * 10000);
        JoinDTO joinDTO = JoinDTO.builder()
                .username("hnazeon")
                .password("password123@")
                .name("박태환")
                .phone("01012345678")
                .email("hnazeon" + "@gmail.com")
                .role(MemberRole.WAREHOUSE_MANAGER)
                .zipCode("04529")
                .roadNameAddress("서울특별시 중구 남대문시장10길 2")
                .lotNumberAddress("서울특별시 중구 회현동1가 204")
                .detailAddress("메사빌딩 21층")
                .businessNumber(null)
                .build();
        log.info("username: {}", joinDTO.getUsername());
        Assertions.assertTrue(authServiceImpl.save(joinDTO));
    }

    @Test
    void 인증_메일_전송_성공() {
        Member member = Member.builder()
                .username("member" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("name")
                .phone("01012345678")
                .email("hnazeon@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.ADMIN)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();
        memberMapper.insert(member);
        authServiceImpl.processIdInquiry(member.getEmail());

        authServiceImpl.processPasswordInquiry(member.getUsername());
    }

    @Test
    void 인증_메일_전송_실패() {
        Member registeredMember = Member.builder()
                .username("member" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("name")
                .phone("01012345678")
                .email("hnazeon@gmail.com")
                .status(MemberStatus.REGISTERED)
                .role(MemberRole.ADMIN)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();

        // 탈퇴한 회원
        Member leavedMember = Member.builder()
                .username("member" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("name")
                .phone("01012345678")
                .email("hnazeon@gmail.com")
                .status(MemberStatus.LEAVED)
                .role(MemberRole.ADMIN)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();
        memberMapper.insert(registeredMember);
        memberMapper.insert(leavedMember);

        // 존재하지 않는 이메일
        Assertions.assertThrows(BizException.class, () -> {
            authServiceImpl.processIdInquiry(UUID.randomUUID().toString().substring(0, 30));
        });

        // 존재하지 않는 회원
        Assertions.assertThrows(BizException.class, () -> {
            authServiceImpl.processPasswordInquiry(UUID.randomUUID().toString().substring(0, 10));
        });
    }
}
