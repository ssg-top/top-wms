package com.top.effitopia.domain;

import com.top.effitopia.dto.MemberDTO;
import com.top.effitopia.enumeration.WarehouseType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {
    private Integer id;

    private Member member;

    private WarehouseType type;

    private String code;

    private String name;

    private String phone;

    private Address address;

    private int	width;

    private int	length;

    private int	height;

    private int	capacity;

    private double latitude;

    private double longitude;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
