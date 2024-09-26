package com.top.effitopia.domain;

import com.top.effitopia.enumeration.ContractStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    private Integer id;

    private Warehouse warehouse;

    private Member member;

    private ContractStatus status;

    private LocalDateTime startDate;

    private int date;

    private LocalDateTime endDate;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
