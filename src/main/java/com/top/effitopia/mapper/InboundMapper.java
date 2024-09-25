package com.top.effitopia.mapper;

import com.top.effitopia.domain.Inbound;
import com.top.effitopia.dto.InboundSearchCond;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InboundMapper {

    int insert(Inbound inbound);

    int update(Inbound inbound);

    int delete(int inboundId);

    Optional<Inbound> selectOne(int inboundId);

    List<Inbound> selectAllList();

    int insertList(List<Inbound> inboundList);

    int updateList(List<Inbound> inboundList);

    int deleteList(List<Integer> inboundIds);

    boolean existsByXxx(InboundSearchCond inboundSearchCond);
}
