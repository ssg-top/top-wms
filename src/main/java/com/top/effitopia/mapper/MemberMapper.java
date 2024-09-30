package com.top.effitopia.mapper;

import com.top.effitopia.domain.Member;
import com.top.effitopia.dto.MemberSearchCond;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.enumeration.MemberStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    int insert(Member member);

    Optional<Member> selectOne(int id);
    Optional<Member> selectOneByUsername(String username);

    List<Member> selectAll(PageRequestDTO<MemberSearchCond> pageRequestDTO);

    int selectCount(PageRequestDTO<MemberSearchCond> pageRequestDTO);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    int update(Member member);

    int updateStatus(@Param("id") int id, @Param("status") MemberStatus status);

    boolean updatePassword(@Param("id")int id, @Param("status") String encodedPassword);
    List<Member> selectAssignableWarehouseManagerList();


}
