package com.top.effitopia.mapper;

import com.top.effitopia.domain.Inbound;
import com.top.effitopia.dto.InboundSearchCond;
import com.top.effitopia.dto.PageRequestDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InboundMapper {

    int insert(Inbound inbound);

    int update(Inbound inbound);

    int delete(int inboundId);

    Optional<Inbound> selectOne(int inboundId);

    List<Inbound> selectAllList(PageRequestDTO<InboundSearchCond> pageRequestDTO);

    int getCount(InboundSearchCond searchCond);

    int approveList(List<Integer> inboundList);

    int completeList(List<Integer> inboundList);

    int deleteList(List<Integer> inboundList);

}
