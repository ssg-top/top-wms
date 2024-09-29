package com.top.effitopia.service;

import com.top.effitopia.domain.Dispatch;
import com.top.effitopia.domain.Outbound;
import com.top.effitopia.domain.TransportVehicle;
import com.top.effitopia.dto.DispatchDTO;
import com.top.effitopia.dto.OutboundDTO;
import com.top.effitopia.dto.TransportVehicleDTO;
import com.top.effitopia.dto.TransportVehicleTemperatureTypeDTO;
import com.top.effitopia.enumeration.DispatchStatus;
import com.top.effitopia.enumeration.OutboundStatus;
import com.top.effitopia.mapper.DispatchMapper;
import com.top.effitopia.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class DispatchServiceImpl implements DispatchService {
    private final ModelMapper modelMapper;
    private final DispatchMapper dispatchMapper;
    private final OrderMapper orderMapper;

    @Override
    public boolean registerDispatch(DispatchDTO dispatchDTO) {
        OutboundDTO outboundDTO = dispatchDTO.getOutboundDTO();
        if (outboundDTO.getOutboundStatus() != OutboundStatus.PENDING) {
            return false;
        }

        Dispatch dispatch = modelMapper.map(dispatchDTO, Dispatch.class);
        dispatch = Dispatch.builder()
                .outbound(modelMapper.map(dispatchDTO.getOutboundDTO(), Outbound.class))
                .transportVehicle(modelMapper.map(dispatchDTO.getTransportVehicleDTO(), TransportVehicle.class))
                .regDate(LocalDateTime.now())
                .dispatchStatus(DispatchStatus.ASSIGNED)
                .build();

        dispatchMapper.insertDispatch(dispatch);
        return true;
    }

    @Override
    public boolean updateDispatch(DispatchDTO dispatchDTO) {
        OutboundDTO outboundDTO = dispatchDTO.getOutboundDTO();
        if (outboundDTO.getOutboundStatus() != OutboundStatus.PENDING) {
            return false;
        }

        Dispatch dispatch = Dispatch.builder()
                .dispatchId(dispatchDTO.getDispatchId())
                .outbound(modelMapper.map(dispatchDTO.getOutboundDTO(), Outbound.class))
                .transportVehicle(modelMapper.map(dispatchDTO.getTransportVehicleDTO(), TransportVehicle.class))
                .modDate(LocalDateTime.now())
                .dispatchStatus(DispatchStatus.ASSIGNED)
                .build();

        dispatchMapper.updateDispatch(dispatch);
        return true;
    }

    @Override
    public boolean deleteDispatch(Integer dispatchId) {
        Dispatch dispatch = dispatchMapper.findById(dispatchId);
        if (dispatch == null || dispatch.getOutbound().getOutboundStatus() != OutboundStatus.PENDING) {
            return false;
        }

        dispatchMapper.deleteDispatch(dispatchId);
        return true;
    }

    @Override
    public List<TransportVehicleDTO> getAvailableVehicles(Integer outboundId) {
        String storageType = orderMapper.findById(outboundId)
                .getStock().getProduct().getProductStorageType().name();

        List<TransportVehicle> vehicles = dispatchMapper.findAvailableVehiclesByStorageType(storageType);

        return vehicles.stream().map(vehicle -> {
            TransportVehicleDTO dto = new TransportVehicleDTO();
            dto.setId(vehicle.getId());
            dto.setCarNum(vehicle.getCarNum());

            TransportVehicleTemperatureTypeDTO temperatureDTO = new TransportVehicleTemperatureTypeDTO();
            temperatureDTO.setTransportVehicleTemperatureTypeName(vehicle.getTransportVehicleTemperatureType().getTransportVehicleTemperatureTypeName());
            dto.setTransportVehicleTemperatureTypeDTO(temperatureDTO);

            return dto;
        }).collect(Collectors.toList());
    }

}
