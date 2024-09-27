package com.top.effitopia.service;

import com.top.effitopia.dto.InboundDTO;
import java.util.List;
import java.util.Optional;

public interface InboundService {

    boolean save(InboundDTO inboundDTO);

    boolean modify(InboundDTO inboundDTO);

    boolean approveInboundRequests(List<InboundDTO> inboundDTOList);

    boolean remove(int inboundId);

    Optional<InboundDTO> get(int inboundId);

    List<InboundDTO> getList();

    void saveList(List<InboundDTO> inboundDTOList);

    void removeList(List<Integer> inboundIdList);
}
