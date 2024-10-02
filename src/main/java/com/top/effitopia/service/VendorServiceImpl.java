package com.top.effitopia.service;

import com.top.effitopia.domain.Inbound;
import com.top.effitopia.domain.Vendor;
import com.top.effitopia.dto.InboundDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.VendorDTO;
import com.top.effitopia.mapper.VendorMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService{

    private final VendorMapper vendorMapper;
    private final ModelMapper modelMapper;


    @Override
    public boolean save(VendorDTO vendorDTO) {
        Vendor vendor = modelMapper.map(vendorDTO, Vendor.class);
        return vendorMapper.insert(vendor) > 0;
    }

    @Override
    public boolean modify(VendorDTO vendorDTO) {
        Vendor vendor = modelMapper.map(vendorDTO, Vendor.class);
        return vendorMapper.update(vendor) > 0;
    }

    @Override
    public boolean remove(int id) {
        return vendorMapper.delete(id) > 0;
    }

    @Override
    public Optional<VendorDTO> get(int vendorId) {
        return Optional.empty();
    }

    @Override
    public PageResponseDTO<VendorDTO> getList(PageRequestDTO<VendorDTO> pageRequestDTO) {

        List<Vendor> vendorList = vendorMapper.selectAllList(pageRequestDTO);
        int total = vendorMapper.getTotalCount(pageRequestDTO);

        List<VendorDTO> dtoList = vendorList.stream()
            .map(vendor -> modelMapper.map(vendor, VendorDTO.class))
            .toList();

        return PageResponseDTO.<VendorDTO>withAll()
            .pageRequestDTO(pageRequestDTO)
            .dtoList(dtoList)
            .total(total)
            .build();
    }




    @Override
    public void saveList(List<VendorDTO> vendorDTOList) {

    }

    @Override
    public void removeList(List<Integer> vendorIdList) {

    }
}
