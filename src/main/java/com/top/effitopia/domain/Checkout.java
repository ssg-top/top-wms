package com.top.effitopia.domain;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Checkout {
    private Integer id;
    private Warehouse warehouse;
    private LocalDateTime regDate;
    private String checkoutWarehouseCode;
    private String checkoutName;
}
