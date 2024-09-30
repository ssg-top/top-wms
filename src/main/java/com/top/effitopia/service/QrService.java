package com.top.effitopia.service;

import com.top.effitopia.dto.InboundDTO;
import com.top.effitopia.dto.QrDTO;
import java.util.List;
import java.util.Optional;

public interface QrService {

    void save(QrDTO qrDTO);

    Optional<QrDTO> get(int qrId);

    String generateQrCodeImage(InboundDTO inboundDTO);
}
