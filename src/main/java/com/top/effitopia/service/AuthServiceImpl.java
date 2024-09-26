package com.top.effitopia.service;

import com.top.effitopia.domain.Address;
import com.top.effitopia.domain.Member;
import com.top.effitopia.dto.MemberDTO;
import com.top.effitopia.dto.PasswordUpdateDTO;
import com.top.effitopia.enumeration.MemberRole;
import com.top.effitopia.enumeration.MemberStatus;
import com.top.effitopia.dto.JoinDTO;
import com.top.effitopia.exception.BizException;
import com.top.effitopia.exception.ErrorCode;
import com.top.effitopia.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean save(JoinDTO joinDTO) {
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

    @Override
    public MemberDTO getOne(int id) {
        Member member = memberMapper.selectOne(id)
                .orElseThrow(() -> new BizException(ErrorCode.NOT_EXISTS_MEMBER));
        return MemberDTO.from(member);
    }

    @Override
    public List<MemberDTO> getList() {
        return memberMapper.selectAll().stream()
                .map(MemberDTO::from)
                .toList();
    }

    @Override
    public boolean modify(MemberDTO dto) {
        Member updateMember = Member.builder()
                .id(dto.getId())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .address(Address.builder()
                        .zipCode(dto.getZipCode())
                        .roadNameAddress(dto.getRoadNameAddress())
                        .lotNumberAddress(dto.getLotNumberAddress())
                        .detailAddress(dto.getDetailAddress())
                        .build())
                .build();
        return memberMapper.update(updateMember) == 1;
    }

    @Override
    public boolean modifyStatus(Member member, MemberStatus memberStatus) {
        if(memberStatus.equals(member.getStatus())) {
            return false;
        }
        return memberMapper.updateStatus(member.getId(), memberStatus) == 1;
    }

    @Override
    public boolean modifyPassword(Member member, PasswordUpdateDTO dto) {
        if(!dto.getNewPassword().equals(dto.getNewPasswordConfirm())) {
            throw new BizException(ErrorCode.NOT_MATCHES_NEW_PASSWORD_CONFIRM);
        } else if(!passwordEncoder.matches(dto.getCurrentPassword(), member.getPassword())){
            throw new BizException(ErrorCode.NOT_MATCHES_PASSWORD);
        } else if(passwordEncoder.matches(dto.getNewPassword(), member.getPassword())) {
            throw new BizException(ErrorCode.DUPLICATE_PASSWORD);
        }
        String encodedPassword = passwordEncoder.encode(dto.getNewPassword());
        return memberMapper.updatePassword(member.getId(), encodedPassword);
    }

    @Override
    public boolean checkDuplicateUsername(String username) {
        return memberMapper.existsByUsername(username);
    }

    @Override
    public boolean checkDuplicateEmail(String email) {
        return memberMapper.existsByEmail(email);
    }


}
