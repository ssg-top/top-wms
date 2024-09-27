package com.top.effitopia.service;

import com.top.effitopia.domain.Member;
import com.top.effitopia.dto.JoinDTO;
import com.top.effitopia.dto.MemberDTO;
import com.top.effitopia.dto.PasswordUpdateDTO;
import com.top.effitopia.enumeration.MemberStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {

    MemberDTO getOne(int id);

    List<MemberDTO> getList();

    boolean modify(MemberDTO memberDTO);

    boolean save(JoinDTO joinDTO);

    boolean modifyStatus(Member member, MemberStatus memberStatus);

    boolean modifyPassword(Member member, PasswordUpdateDTO dto);

    boolean checkDuplicateUsername(String username);

    boolean checkDuplicateEmail(String email);

    String processIdInquiry(String email);

    String processPasswordInquiry(String username);

    boolean verifyMailCode(String email, String code);
}
