package com.top.effitopia.domain;

import com.top.effitopia.enumeration.ContractStatus;

import java.time.LocalDateTime;

public class Contract {
    private long contract_id;
    private Warehouse warehouse;
    private ContractStatus contract_status;
    private Member member;
    private LocalDateTime contract_start_date;
    private int contract_date;
    private LocalDateTime contract_end_date;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;
}
