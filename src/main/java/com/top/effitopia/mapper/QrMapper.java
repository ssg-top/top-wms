package com.top.effitopia.mapper;

import com.top.effitopia.domain.Qr;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QrMapper {

    int insert(Qr qr);  // QR 코드 저장

    Optional<Qr> selectOne(@Param("qrId") int qrId);

}
