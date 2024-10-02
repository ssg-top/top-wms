package com.top.effitopia.mapper;


import com.top.effitopia.domain.Vendor;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.VendorDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VendorMapper {

    int insert(Vendor vendor);

    int update(Vendor vendor);

    int delete(int id);

    List<Vendor> selectAllList(PageRequestDTO<VendorDTO> pageRequestDTO);

    void deleteList(@Param("ids") List<Integer> ids);


    int getTotalCount(PageRequestDTO<VendorDTO> pageRequestDTO);
}
