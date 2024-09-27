package com.top.effitopia.dto;

import com.top.effitopia.domain.Inbound;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

    private Integer id;
    private String name;
    private boolean delFlag;

}
