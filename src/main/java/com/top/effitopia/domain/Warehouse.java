package com.top.effitopia.domain;

import com.top.effitopia.enumeration.WarehouseType;

import java.time.LocalDateTime;

public class Warehouse {
    private long warehouse_id;
    private Member member;
    private WarehouseType warehouse_type;
    private String warehouse_code;
    private String warehouse_name;
    private String warehouse_phone;
    private String warehouse_warehouse_zip_code;
    private String warehouse_lot_number;
    private String warehouse_detail_address;
    private int	warehouse_width;
    private int	warehouse_length;
    private int	warehouse_height;
    private int	warehouse_capacity;
    private double warehouse_latitude;
    private double warehouse_longitube;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;
}
