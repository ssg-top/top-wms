package com.top.effitopia.mapper;

import com.top.effitopia.domain.Dispatch;
import com.top.effitopia.domain.TransportVehicle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DispatchMapper {
    void insertDispatch(Dispatch dispatch);

    void updateDispatch(Dispatch dispatch);

    void deleteDispatch(Integer dispatchId);

    Dispatch findById(Integer dispatchId);

    List<TransportVehicle> findAvailableVehiclesByStorageType(String storageType);
}
