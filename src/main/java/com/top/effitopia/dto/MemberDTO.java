package com.top.effitopia.dto;

import com.top.effitopia.domain.Member;
import com.top.effitopia.enumeration.MemberRole;
import com.top.effitopia.enumeration.MemberStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MemberDTO {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private MemberStatus status;
    private MemberRole role;
    private String zipCode;
    private String roadNameAddress;
    private String lotNumberAddress;
    private String detailAddress;
    private String businessNumber;
    private LocalDateTime regDate;

    public static MemberDTO from(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .phone(member.getPhone())
                .email(member.getEmail())
                .status(member.getStatus())
                .role(member.getRole())
                .zipCode(member.getAddress().getZipCode())
                .roadNameAddress(member.getAddress().getRoadNameAddress())
                .lotNumberAddress(member.getAddress().getLotNumberAddress())
                .detailAddress(member.getAddress().getDetailAddress())
                .businessNumber(member.getBusinessNumber())
                .regDate(member.getRegDate())
                .build();
    }
}
