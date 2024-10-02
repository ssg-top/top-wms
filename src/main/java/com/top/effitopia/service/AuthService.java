package com.top.effitopia.service;

import com.top.effitopia.domain.Member;
import com.top.effitopia.dto.*;
import com.top.effitopia.enumeration.MemberStatus;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    MemberDTO getOne(int id);
    MemberDTO getOneByEmail(String email);

    PageResponseDTO<MemberDTO> getList(PageRequestDTO<MemberSearchCond> pageRequestDTO);
    int getCount(MemberSearchCond pageRequestDTO);

    boolean modify(MemberDTO memberDTO);

    boolean save(JoinDTO joinDTO);

    boolean modifyStatus(Member member, MemberStatus memberStatus);

    boolean modifyPassword(Member member, PasswordUpdateDTO dto);

    boolean checkDuplicateUsername(String username);

    boolean checkDuplicateEmail(String email);

    void processIdInquiry(String email);

    void processPasswordInquiry(String username);

    boolean verifyMailCode(String email, String code);

}
