package com.top.effitopia.dto;

import com.top.effitopia.enumeration.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinDTO {

    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private MemberRole role;
    private String zipCode;
    private String roadNameAddress;
    private String lotNumberAddress;
    private String detailAddress;
    private String businessNumber;
}
