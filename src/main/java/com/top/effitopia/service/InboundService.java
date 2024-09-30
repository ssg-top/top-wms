package com.top.effitopia.service;

import com.top.effitopia.dto.InboundDTO;
import java.util.List;
import java.util.Optional;

public interface InboundService {

    boolean save(InboundDTO inboundDTO);

    boolean modify(InboundDTO inboundDTO);

    boolean approveInboundRequests(List<Integer> inboundIds);

    boolean completeInboundRequests(List<Integer> inboundIds);

    boolean remove(int inboundId);

    Optional<InboundDTO> get(int inboundId);

    List<InboundDTO> getList();

    void removeList(List<Integer> inboundIds);
}
