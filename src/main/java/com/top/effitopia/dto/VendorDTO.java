package com.top.effitopia.dto;

import com.top.effitopia.domain.Inbound;
import com.top.effitopia.domain.Vendor;
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
    private String phone;
    private String address;
    private boolean deleted;

    public Vendor toEntity() {
        return Vendor.builder()
            .id(this.id)
            .name(this.name)
            .phone(this.phone)
            .address(this.address)
            .deleted(this.deleted)
            .build();
    }

    public static VendorDTO fromEntity(Vendor vendor) {
        return VendorDTO.builder()
            .id(vendor.getId())
            .name(vendor.getName())
            .phone(vendor.getPhone())
            .address(vendor.getAddress())
            .deleted(vendor.isDeleted())
            .build();
    }

}
