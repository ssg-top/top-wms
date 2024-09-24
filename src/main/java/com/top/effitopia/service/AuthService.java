package com.top.effitopia.service;

import com.top.effitopia.domain.Address;
import com.top.effitopia.domain.Member;
import com.top.effitopia.enumeration.MemberRole;
import com.top.effitopia.enumeration.MemberStatus;
import com.top.effitopia.dto.JoinDTO;
import com.top.effitopia.exception.BizException;
import com.top.effitopia.exception.ErrorCode;
import com.top.effitopia.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public boolean join(JoinDTO joinDTO) {
        if(checkDuplicateUsername(joinDTO.getUsername())) {
            throw new BizException(ErrorCode.DUPLICATE_USERNAME);
        } else if(checkDuplicateEmail(joinDTO.getEmail())) {
            throw new BizException(ErrorCode.DUPLICATE_EMAIL);
        } else if(MemberRole.BUSINESS_PROPRIETOR.equals(joinDTO.getRole()) && joinDTO.getBusinessNumber() == null) {
            // TODO: 사업자 번호 유효성 검증
            throw new BizException(ErrorCode.INVALID_BUSINESS_NUMBER);
        }
        Member member = Member.builder()
                .username(joinDTO.getUsername())
                .password(passwordEncoder.encode(joinDTO.getPassword()))
                .name(joinDTO.getName())
                .phone(joinDTO.getPhone())
                .email(joinDTO.getEmail())
                .role(joinDTO.getRole())
                .status(MemberStatus.REGISTER_REQUEST)
                .address(Address.builder()
                        .zipCode(joinDTO.getZipCode())
                        .roadNameAddress(joinDTO.getRoadNameAddress())
                        .lotNumberAddress(joinDTO.getLotNumberAddress())
                        .detailAddress(joinDTO.getDetailAddress())
                        .build())
                .businessNumber(joinDTO.getBusinessNumber())
                .build();
        return memberMapper.insert(member) > 0;
    }

    public boolean checkDuplicateUsername(String username) {
        return memberMapper.existsByUsername(username);
    }

    public boolean checkDuplicateEmail(String email) {
        return memberMapper.existsByEmail(email);
    }


}
