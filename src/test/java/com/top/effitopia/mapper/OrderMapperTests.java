package com.top.effitopia.mapper;

import com.top.effitopia.domain.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Log4j2
//@Transactional
public class OrderMapperTests {

    @Autowired
    private OrderMapper orderMapper;

    private Stock stock;

    @BeforeEach
    public void setUp() {
        stock = Stock.builder()
                .id(1)
                .build();
    }

    @Test
    public void testInsertOrder() {

        Order order = Order.builder()
                .buyerName("Test Buyer")
                .zipCode("12345")
                .buyerRoadName("Test Road")
                .buyerLotNumber("123")
                .buyerDetailAddress("Test Address")
                .buyerLatitude(37.123456)
                .buyerLongitude(127.123456)
                .buyerQuantity(10)
                .stock(stock)
                .build();

        orderMapper.insertOrder(order);
        assertThat(order.getOrderId()).isNotNull();
    }

    @Test
    public void testUpdateOrder() {
        Order order = Order.builder()
                .orderId(1)
                .buyerName("Updated Buyer")
                .zipCode("54321")
                .buyerRoadName("Updated Road")
                .buyerLotNumber("321")
                .buyerDetailAddress("Updated Address")
                .buyerLatitude(37.654321)
                .buyerLongitude(127.654321)
                .buyerQuantity(20)
                .build();

        orderMapper.updateOrder(order);
        log.info("Order updated successfully");
    }
}
