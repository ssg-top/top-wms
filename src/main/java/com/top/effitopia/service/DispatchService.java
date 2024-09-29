package com.top.effitopia.service;

import com.top.effitopia.dto.DispatchDTO;
import com.top.effitopia.dto.TransportVehicleDTO;

import java.util.List;

public interface DispatchService {

    boolean registerDispatch(DispatchDTO dispatchDTO);

    boolean updateDispatch(DispatchDTO dispatchDTO);

    boolean deleteDispatch(Integer dispatchId);

    List<TransportVehicleDTO> getAvailableVehicles(Integer outboundId);
}
