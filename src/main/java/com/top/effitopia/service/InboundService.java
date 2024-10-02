package com.top.effitopia.service;

import com.top.effitopia.dto.InboundDTO;
import com.top.effitopia.dto.InboundSearchCond;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import java.util.List;
import java.util.Optional;

public interface InboundService {

    boolean save(InboundDTO inboundDTO);

    boolean modify(InboundDTO inboundDTO);

    boolean approveInboundRequests(List<Integer> inboundIds);

    boolean completeInboundRequests(List<Integer> inboundIds);

    boolean remove(int inboundId);

    Optional<InboundDTO> get(int inboundId);

    PageResponseDTO<InboundDTO> getList(PageRequestDTO<InboundSearchCond> pageRequestDTO);

    void removeList(List<Integer> inboundIds);
}
