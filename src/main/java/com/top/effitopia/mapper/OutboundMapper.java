package com.top.effitopia.mapper;

import com.top.effitopia.domain.Outbound;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.enumeration.OutboundStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OutboundMapper {
    void insertOutbound(Outbound outbound);

    List<Outbound> selectList(PageRequestDTO pageRequestDTO);

    int getTotalCount(PageRequestDTO pageRequestDTO);

    Outbound selectDetail(Integer outboundId);

    void updateStatus(@Param("outboundId") Integer outboundId, @Param("status") OutboundStatus status);
}
