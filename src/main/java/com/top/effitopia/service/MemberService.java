package com.top.effitopia.service;

import com.top.effitopia.domain.Member;
import com.top.effitopia.dto.MemberDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    boolean save(Member dto);

    MemberDTO getOne(int id);

    List<MemberDTO> getList();

}
