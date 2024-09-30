package com.top.effitopia.service;

import com.top.effitopia.domain.Address;
import com.top.effitopia.domain.Member;
import com.top.effitopia.dto.*;
import com.top.effitopia.enumeration.MemberRole;
import com.top.effitopia.enumeration.MemberStatus;
import com.top.effitopia.exception.BizException;
import com.top.effitopia.exception.ErrorCode;
import com.top.effitopia.mapper.MemberMapper;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final RedisService redisService;

    @Value("${mail.template-path}")
    private String mailTemplatePath;
    @Value("${mail.title.id-inquiry}")
    private String idInquiryMailTitle;
    @Value("${mail.title.password-inquiry}")
    private String passwordInquiryMailTitle;
    @Value("${mail.auth_code_expiration_time}")
    private String authCodeExpirationTime;


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
    public PageResponseDTO<MemberDTO> getList(PageRequestDTO<MemberSearchCond> pageRequestDTO) {
        List<MemberDTO> dtoList = memberMapper.selectAll(pageRequestDTO).stream()
                .map(MemberDTO::from)
                .toList();
        int totalCount = memberMapper.selectCount(pageRequestDTO);
        return new PageResponseDTO<>(pageRequestDTO, dtoList, totalCount);
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

    @Override
    public String processIdInquiry(String email) {
        if(!memberMapper.existsByEmail(email)) {
            throw new BizException(ErrorCode.NOT_EXISTS_EMAIL);
        }
        return sendAuthMail(email, idInquiryMailTitle);
    }

    @Override
    public String processPasswordInquiry(String username) {
        Member member = memberMapper.selectOneByUsername(username)
                .orElseThrow(() -> new BizException(ErrorCode.NOT_EXISTS_MEMBER));
        if(MemberStatus.LEAVED.equals(member.getStatus())) {
            throw new BizException(ErrorCode.NOT_EXISTS_MEMBER);
        }
        return sendAuthMail(member.getEmail(), passwordInquiryMailTitle);
    }

    @Override
    public boolean verifyMailCode(String email, String code) {
        String findCode = redisService.getValue(email);
        if (findCode == null) {
            return false;
        }
        return findCode.equals(code);
    }

    private String sendAuthMail(String email, String title) {
        Map<String, String> params = new HashMap<>();
        String code = generateCode();
        params.put("code", code);
        try {
            mailService.sendMail(title, email, mailTemplatePath, params);
            redisService.setValues(email, code);
        } catch (MessagingException e) {
            throw new BizException(ErrorCode.FAIL_TO_SEND_MAIL);
        }
        return code;
    }

    /**
     * 랜덤 숫자 코드 6자리 생성
     *
     * @return
     */
    private String generateCode() {
        int codeLength = 6;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < codeLength; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
