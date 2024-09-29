package com.top.effitopia.service;

import com.top.effitopia.domain.*;
import com.top.effitopia.dto.*;
import com.top.effitopia.enumeration.OutboundStatus;
import com.top.effitopia.enumeration.WaybillStatus;
import com.top.effitopia.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class OutboundServiceImpl implements OutboundService {

    private final OrderMapper orderMapper;
    private final ModelMapper modelMapper;

    @Override
    public void registerOrder(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        orderMapper.insertOrder(order);

        OutboundDTO outboundDTO = OutboundDTO.builder()
                .orderDTO(orderDTO)
                .outboundStatus(OutboundStatus.PENDING)
                .regDate(LocalDateTime.now())
                .build();
        Outbound outbound = modelMapper.map(outboundDTO, Outbound.class);
        orderMapper.insertOutbound(outbound);
    }

    @Override
    public void updateOrder(Integer id, OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        orderMapper.updateOrder(order);

        Outbound outbound = orderMapper.findOutboundByOrderId(id);
        OutboundDTO outboundDTO = OutboundDTO.builder()
                .modDate(LocalDateTime.now())
                .build();
        outbound = modelMapper.map(outboundDTO, Outbound.class);
        orderMapper.updateOutbound(outbound);
    }

    @Override
    public void updateOrderStatus(Integer id, OutboundStatus status) {
        Outbound outbound = orderMapper.findOutboundByOrderId(id);
        if (outbound != null) {
            OutboundDTO outboundDTO = OutboundDTO.builder()
                    .outboundStatus(status)
                    .modDate(LocalDateTime.now())
                    .build();
            outbound = modelMapper.map(outboundDTO, Outbound.class);
            orderMapper.updateOutbound(outbound);

            if (status == OutboundStatus.APPROVED) {
                WaybillDTO waybillDTO = WaybillDTO.builder()
                        .waybillNum(generateWaybillNum(outbound.getOutboundId()))
                        .waybillPrice(calculateWaybillPrice(outbound.getOrder().getOrderId()))
                        .regDate(LocalDateTime.now())
                        .estimatedArrivalDate(LocalDateTime.now().plusDays(3))
                        .waybillStatus(WaybillStatus.IN_TRANSIT)
                        .build();
                Waybill waybill = modelMapper.map(waybillDTO, Waybill.class);
                orderMapper.insertWaybill(waybill);

                Order order = orderMapper.findById(outbound.getOrder().getOrderId());
                Stock stock = orderMapper.findStockById(order.getStock().getId());
                if (order != null && stock != null) {
                    TempStock tempStock = TempStock.builder()
                            .changeAmount(order.getBuyerQuantity())
                            .manufacturingDate(stock.getManufacturingDate())
                            .expirationDate(stock.getExpirationDate())
                            .member(order.getMember())
                            .cell(stock.getCell())
                            .product(stock.getProduct())
                            .build();

                    orderMapper.insertTempStock(tempStock);
                }
            }
        }
    }

    private String generateWaybillNum(Integer outboundId) {
        return "WB-" + outboundId + "-" + LocalDateTime.now().toString();
    }

    private double calculateWaybillPrice(Integer orderId) {
        Order order = orderMapper.findById(orderId);
        if (order != null) {
            Integer stockId = order.getStock().getId();

            Stock stock = orderMapper.findStockById(stockId);
            if (stock != null) {
                Integer warehouseId = stock.getCell().getWarehouse().getId();

                Product product = orderMapper.findProductByStockId(stockId);

                Integer outboundBasicFee = orderMapper.findOutboundBasicFeeByWarehouseId(warehouseId);

                if (product != null && outboundBasicFee != null) {
                    return product.getProductWeight() * outboundBasicFee;
                }
            }
        }
        return 0.0;
    }

    @Override
    public List<OrderDTO> getOrders(PageRequestDTO pageRequestDTO) {
        List<Order> orders = orderMapper.findAll(pageRequestDTO);
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DetailsDTO> getOrderDetails(Integer id) {
        return Optional.ofNullable(orderMapper.findById(id))
                .map(order -> {
                    OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

                    Outbound outbound = orderMapper.findOutboundByOrderId(order.getOrderId());
                    OutboundDTO outboundDTO = (outbound != null)
                            ? modelMapper.map(outbound, OutboundDTO.class)
                            : null;

                    Dispatch dispatch = (outbound != null)
                            ? orderMapper.findDispatchByOutboundId(outbound.getOutboundId())
                            : null;
                    DispatchDTO dispatchDTO = (dispatch != null)
                            ? modelMapper.map(dispatch, DispatchDTO.class)
                            : null;

                    Waybill waybill = (outbound != null)
                            ? orderMapper.findWaybillByOutboundId(outbound.getOutboundId())
                            : null;
                    WaybillDTO waybillDTO = (waybill != null)
                            ? modelMapper.map(waybill, WaybillDTO.class)
                            : null;

                    return DetailsDTO.builder()
                            .orderDTO(orderDTO)
                            .outboundDTO(outboundDTO)
                            .dispatchDTO(dispatchDTO)
                            .waybillDTO(waybillDTO)
                            .build();
                });
    }

}
