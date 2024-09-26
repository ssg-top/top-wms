package com.top.effitopia.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Checkout {
    private Integer checkoutId;
    private Warehouse warehouse;
    private String checkoutContent;
    private LocalDateTime regDate;
}
