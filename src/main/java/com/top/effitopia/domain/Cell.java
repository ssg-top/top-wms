package com.top.effitopia.domain;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cell {
    private long id;
    private Warehouse warehouse;
    private int	code;
    private int	width;
    private int	length;
    private int	height;
    private int	capacity;
}
