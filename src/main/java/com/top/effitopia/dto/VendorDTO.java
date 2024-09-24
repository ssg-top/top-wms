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

    private int vendorId;   //거래처아이디
    private String vendorName;  //거래처명
    private Inbound inbound;    //입고아이디

}
