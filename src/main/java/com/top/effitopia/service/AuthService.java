package com.top.effitopia.service;

import com.top.effitopia.dto.JoinDTO;
import com.top.effitopia.dto.MemberDTO;
import com.top.effitopia.enumeration.MemberStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {


    MemberDTO getOne(int id);

    List<MemberDTO> getList();

    boolean modify(MemberDTO memberDTO);
    boolean modifyPassword();
    boolean modifyStatus(int id, MemberStatus memberStatus);

    boolean save(JoinDTO joinDTO);

    boolean checkDuplicateUsername(String username);

    boolean checkDuplicateEmail(String email);

}
