package com.top.effitopia.service;

import com.top.effitopia.domain.Contract;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.domain.WarehouseCost;
import com.top.effitopia.dto.*;

import java.util.List;
import java.util.Optional;

public interface ContractService {
    boolean save(ContractDTO contractDTO);
    PageResponseDTO getListAll(PageRequestDTO pageRequestDTO);
    PageResponseDTO getListByStatus(PageRequestDTO pageRequestDTO);
    boolean modify(ContractDTO contractDTO);
    void modifyApprovalList(List<Integer> id);
    void modifyRejectList(List<Integer> id);
    Contract changedVO(ContractDTO contractDTO);
    Optional<WarehouseCostDTO> get(Integer id);
}
