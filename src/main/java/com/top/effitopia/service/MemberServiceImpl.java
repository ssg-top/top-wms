package com.top.effitopia.service;

import com.top.effitopia.domain.Member;
import com.top.effitopia.dto.MemberDTO;
import com.top.effitopia.exception.BizException;
import com.top.effitopia.exception.ErrorCode;
import com.top.effitopia.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public boolean save(Member member) {
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


}
