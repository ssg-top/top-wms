package com.top.effitopia.domain;

import com.top.effitopia.dto.MemberDTO;
import com.top.effitopia.dto.ProductDTO;
import com.top.effitopia.dto.WarehouseDTO;
import com.top.effitopia.enumeration.InboundStatus;
import java.time.LocalDate;


public class Inbound {

    private int inboundId;
    private Member member;
    private Warehouse warehouse;
    private Product product;
    private LocalDate inboundRequestDate;
    private LocalDate inboundApprovedDate;
    private LocalDate inboundExpectDate;
    private LocalDate inboundCompletedDate;
    private InboundStatus inboundStatus;

}
