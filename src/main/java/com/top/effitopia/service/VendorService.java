package com.top.effitopia.service;

import com.top.effitopia.dto.InboundDTO;
import com.top.effitopia.dto.VendorDTO;
import java.util.List;
import java.util.Optional;

public interface VendorService {

    boolean save(VendorDTO vendorDTO);

    boolean modify(VendorDTO vendorDTO);

    boolean remove(int vendorId);

    Optional<VendorDTO> get(int vendorId);

    List<VendorDTO> getList();

    void saveList(List<VendorDTO> vendorDTOList);

    void removeList(List<Integer> vendorIdList);

}
