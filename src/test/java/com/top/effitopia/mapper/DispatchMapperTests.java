package com.top.effitopia.mapper;

import com.top.effitopia.domain.Dispatch;
import com.top.effitopia.domain.Outbound;
import com.top.effitopia.domain.TransportVehicle;
import com.top.effitopia.dto.DispatchDTO;
import com.top.effitopia.dto.OutboundDTO;
import com.top.effitopia.dto.TransportVehicleDTO;
import com.top.effitopia.enumeration.DispatchStatus;
import com.top.effitopia.enumeration.OutboundStatus;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Log4j2
@Transactional
@Rollback
public class DispatchMapperTests {

    @Autowired
    private DispatchMapper dispatchMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ModelMapper modelMapper;

    private DispatchDTO testDispatchDTO;
    private Outbound outbound;
    private List<TransportVehicle> vehicles;

    @BeforeEach
    void setup() {
        outbound = orderMapper.findOutboundByOrderId(1);
        if (outbound == null) {
            outbound = Outbound.builder()
                    .order(orderMapper.findById(1))
                    .outboundStatus(OutboundStatus.PENDING)
                    .regDate(LocalDateTime.now())
                    .build();
            orderMapper.insertOutbound(outbound);
        }

        assertThat(outbound).isNotNull();

        vehicles = dispatchMapper.findAvailableVehiclesByStorageType("FROZEN");
        assertThat(vehicles).isNotEmpty();

        testDispatchDTO = DispatchDTO.builder()
                .outboundDTO(modelMapper.map(outbound, OutboundDTO.class))
                .transportVehicleDTO(modelMapper.map(vehicles.get(0), TransportVehicleDTO.class))
                .regDate(LocalDateTime.now())
                .dispatchStatus(DispatchStatus.ASSIGNED)
                .build();
        log.info("kfskjdfsjdhfsdhflsdhfsdfkjsdajdsbdfkjsdfjjbskdfjbksdbjfjsdf"+testDispatchDTO);
    }

    @Test
    void testInsertDispatch() {
        log.info("DispatchMapperTests testInsertDispatch");

        Dispatch dispatch = Dispatch.builder()
                .outbound(outbound)
                .transportVehicle(vehicles.get(0))
                .regDate(LocalDateTime.now())
                .dispatchStatus(DispatchStatus.ASSIGNED)
                .build();
        dispatchMapper.insertDispatch(dispatch);

        assertThat(dispatch.getDispatchId()).isNotNull();
        log.info("Inserted Dispatch ID: {}", dispatch.getDispatchId());
    }


    @Test
    void testFindById() {
        log.info("DispatchMapperTests testFindById");

        Dispatch dispatch = dispatchMapper.findById(1);
        assertThat(dispatch).isNotNull();
        assertThat(dispatch.getDispatchId()).isEqualTo(1);

        log.info("Found Dispatch: {}", dispatch);
    }

    @Test
    void testFindAllAvailableVehicles() {
        log.info("DispatchMapperTests testFindAllAvailableVehicles");

        List<TransportVehicle> vehicles = dispatchMapper.findAvailableVehiclesByStorageType("FROZEN");

        List<TransportVehicleDTO> vehicleDTOs = vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, TransportVehicleDTO.class))
                .collect(Collectors.toList());

        assertThat(vehicleDTOs).isNotEmpty();
        log.info("Total Available Vehicles: {}", vehicleDTOs.size());
    }

    @Test
    void testUpdateDispatch() {
        log.info("DispatchMapperTests testUpdateDispatch");

        Dispatch dispatch = dispatchMapper.findById(1);

        DispatchDTO updateDispatchDTO = DispatchDTO.builder()
                .dispatchId(dispatch.getDispatchId())
                .outboundDTO(modelMapper.map(dispatch.getOutbound(), OutboundDTO.class))
                .transportVehicleDTO(modelMapper.map(dispatch.getTransportVehicle(), TransportVehicleDTO.class))
                .dispatchStatus(DispatchStatus.ASSIGNED)
                .modDate(LocalDateTime.now())
                .build();

//        Dispatch updatedDispatch = modelMapper.map(updateDispatchDTO, Dispatch.class);
        Dispatch updatedDispatch = Dispatch.builder()
                .outbound(dispatch.getOutbound())
                .transportVehicle(dispatch.getTransportVehicle())
                .dispatchStatus(DispatchStatus.ASSIGNED)
                .modDate(LocalDateTime.now())
                .build();

        dispatchMapper.updateDispatch(updatedDispatch);

        Dispatch resultDispatch = dispatchMapper.findById(1);
        assertThat(resultDispatch.getDispatchStatus()).isEqualTo(DispatchStatus.ASSIGNED);
        log.info("Updated Dispatch: {}", resultDispatch);
    }

    @Test
    void testDeleteDispatch() {
        log.info("DispatchMapperTests testDeleteDispatch");

        dispatchMapper.deleteDispatch(1);
        Dispatch dispatch = dispatchMapper.findById(1);
        assertThat(dispatch).isNull();
        log.info("Dispatch deleted successfully.");
    }
}
