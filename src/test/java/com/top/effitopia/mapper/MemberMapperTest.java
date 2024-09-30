package com.top.effitopia.mapper;

import com.top.effitopia.domain.Address;
import com.top.effitopia.domain.Member;
import com.top.effitopia.enumeration.MemberRole;
import com.top.effitopia.enumeration.MemberStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Slf4j
@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    Member newMember;

    @BeforeEach
    void memberInit() {
        Member admin = Member.builder()
                .username("admin" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("name")
                .phone("01012345678")
                .email("2330914@naver.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.ADMIN)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();

        Member warehouseManager = Member.builder()
                .username("manager" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("name")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.WAREHOUSE_MANAGER)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();

        Member proprietor = Member.builder()
                .username("proprietor" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("name")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.BUSINESS_PROPRIETOR)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .businessNumber("1234567890")
                .build();

        Member dispatcher = Member.builder()
                .username("dispatcher" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("name")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.DISPATCHER)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();

        newMember = admin;
    }


    @Test
    void insert() {
        memberMapper.insert(newMember);
        int id = newMember.getId();
        log.info("insert id: {}", id);
        Member findMember = memberMapper.selectOne(id).get();
        Assertions.assertEquals(newMember.getUsername(), findMember.getUsername());
        Assertions.assertEquals(newMember.getPassword(), findMember.getPassword());
        Assertions.assertEquals(newMember.getName(), findMember.getName());
        Assertions.assertEquals(newMember.getPhone(), findMember.getPhone());
        Assertions.assertEquals(newMember.getEmail(), findMember.getEmail());
        Assertions.assertEquals(newMember.getStatus(), findMember.getStatus());
        Assertions.assertEquals(newMember.getRole(), findMember.getRole());
        Assertions.assertEquals(newMember.getAddress().getRoadNameAddress(), findMember.getAddress().getRoadNameAddress());
        Assertions.assertEquals(newMember.getAddress().getLotNumberAddress(), findMember.getAddress().getLotNumberAddress());
        Assertions.assertEquals(newMember.getAddress().getDetailAddress(), findMember.getAddress().getDetailAddress());
        Assertions.assertEquals(newMember.getBusinessNumber(), findMember.getBusinessNumber());
    }

    @Test
    void updateStatus() {
        memberMapper.insert(newMember);
        int id = newMember.getId();
        int rows = memberMapper.updateStatus(id, MemberStatus.REGISTERED);
        Assertions.assertEquals(rows, 1);
        Member member = memberMapper.selectOne(id).get();
        Assertions.assertEquals(member.getStatus(), MemberStatus.REGISTERED);
    }

    @Test
    void update() {
        memberMapper.insert(newMember);
        int id = newMember.getId();
        Member updateMember = Member.builder()
                .id(id)
                .phone("01011111111")
                .email(UUID.randomUUID().toString().substring(0, 6) + "@gmail.com")
                .address(Address.builder()
                        .zipCode("update address1")
                        .roadNameAddress("update address2")
                        .lotNumberAddress("update address3")
                        .detailAddress("update address4")
                        .build())
                .build();
        Member findMember = memberMapper.selectOne(id).get();
        Assertions.assertEquals(memberMapper.update(updateMember), 1);
        Assertions.assertEquals(findMember.getPhone(), updateMember.getPhone());
        Assertions.assertEquals(findMember.getEmail(), updateMember.getEmail());
    }

}
