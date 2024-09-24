package com.top.effitopia.mapper;

import com.top.effitopia.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    int insert(Member member);

    Optional<Member> selectOne(int id);
    Optional<Member> selectOneByUsername(String username);

    List<Member> selectAll();

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
