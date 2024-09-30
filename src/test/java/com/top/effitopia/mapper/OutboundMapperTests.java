package com.top.effitopia.mapper;

import com.top.effitopia.domain.Outbound;
import com.top.effitopia.domain.Order;
import com.top.effitopia.domain.Stock;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.enumeration.OutboundStatus;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Log4j2
//@Transactional
public class OutboundMapperTests {

//    @Autowired
//    private OutboundMapper outboundMapper;
//
//    @Autowired
//    private OrderMapper orderMapper;
//
//    private Order order;
//    private PageRequestDTO pageRequestDTO;
//
//    @BeforeEach
//    public void setUp() {
//        order = Order.builder()
//                .orderId(1)
//                .build();
//
//        pageRequestDTO = PageRequestDTO.builder()
//                .page(1)
//                .size(10)
//                .build();
//    }
//
//    @Test
//    public void testInsertOutbound() {
//        Stock stock = Stock.builder()
//                .id(1)  // 존재하는 stock ID로 설정
//                .stockAmount(50)
//                .build();
//
//        Order order = Order.builder()
//                .buyerName("Test Buyer")
//                .zipCode("12345")
//                .buyerRoadName("Test Road")
//                .buyerLotNumber("123")
//                .buyerDetailAddress("Test Address")
//                .buyerLatitude(37.123456)
//                .buyerLongitude(127.123456)
//                .buyerQuantity(10)
//                .stock(stock)
//                .build();
//
//        orderMapper.insertOrder(order);
//        assertThat(order.getOrderId()).isNotNull();
//
//        Outbound outbound = Outbound.builder()
//                .order(order)
//                .outboundStatus(OutboundStatus.PENDING)
//                .regDate(LocalDateTime.now())
//                .build();
//
//        outboundMapper.insertOutbound(outbound);
//        assertThat(outbound.getOutboundId()).isNotNull();
//    }
//
//
//    @Test
//    public void testSelectList() {
//        List<Outbound> outboundList = outboundMapper.selectList(pageRequestDTO);
//        assertThat(outboundList).isNotEmpty();
//    }
//
//    @Test
//    public void testGetTotalCount() {
//        int count = outboundMapper.getTotalCount(pageRequestDTO);
//        assertThat(count).isGreaterThan(0);
//    }
//
//    @Test
//    public void testSelectDetail() {
//        Outbound outbound = outboundMapper.selectDetail(3);
//        assertThat(outbound).isNotNull();
//        assertThat(outbound.getOutboundId()).isEqualTo(3);
//    }
//
//    @Test
//    public void testUpdateStatus() {
//        outboundMapper.updateStatus(3, OutboundStatus.APPROVED);
//        Outbound updatedOutbound = outboundMapper.selectDetail(3);
//        assertThat(updatedOutbound.getOutboundStatus()).isEqualTo(OutboundStatus.APPROVED);
//    }
}
