package com.top.effitopia.service;

import com.top.effitopia.domain.Inbound;
import com.top.effitopia.dto.InboundDTO;
import com.top.effitopia.dto.InboundSearchCond;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.QrDTO;
import com.top.effitopia.mapper.InboundMapper;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class InboundServiceImpl implements InboundService {

    private final InboundMapper inboundMapper;
    private final QrService qrService;
    private final ModelMapper modelMapper;

    /**
     * InboundDTO 를 받아 새로운 입고 데이터를 생성하는 메서드
     *
     * @param inboundDTO InboundDTO 객체
     * @return 성공적으로 데이터가 저장되면 true, 그렇지 않으면 false
     */
    @Override
    public boolean save(InboundDTO inboundDTO) {
        Inbound inbound = modelMapper.map(inboundDTO, Inbound.class);
        return inboundMapper.insert(inbound) > 0;
    }

    /**
     * 특정 입고 데이터를 수정하는 메서드
     *
     * @param inboundDTO 수정할 InboundDTO 객체
     * @return 수정이 성공하면 true, 실패하면 false
     */
    @Override
    public boolean modify(InboundDTO inboundDTO) {
        Inbound inbound = modelMapper.map(inboundDTO, Inbound.class);
        return inboundMapper.update(inbound) > 0;
    }

    /**
     * 특정 입고 요청 리스트를 승인하는 메서드 추가적으로 입고가 승인되면 QR 코드를 생성함
     *
     * @param inboundIds 승인할 입고 요청들의 ID 리스트
     * @return 승인이 성공하면 true, 실패하면 false
     */
    @Override
    @Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        rollbackFor = {Exception.class, RuntimeException.class},
        timeout = 30
    )
    public boolean approveInboundRequests(List<Integer> inboundIds) {

        boolean success = inboundMapper.approveList(inboundIds) > 0;

        if (success) {
            for (Integer inboundId : inboundIds) {
                Inbound inbound = inboundMapper.selectOne(inboundId)
                    .orElseThrow(
                        () -> new IllegalArgumentException("해당하는 입고요청이 존재하지 않습니다.: " + inboundId));

                InboundDTO inboundDTO = InboundDTO.fromEntity(inbound);

                String qrImgPath = qrService.generateQrCodeImage(inboundDTO);

                QrDTO qrDTO = QrDTO.builder()
                    .inboundDTO(inboundDTO)
                    .qrImg(qrImgPath)
                    .build();

                qrService.save(qrDTO);
            }
        }

        return success;
        //return inboundMapper.approveList(inboundIds) > 0;
    }


    /**
     * 특정 입고 요청 리스트를 완료하는 메서드
     *
     * @param inboundIds 완료 처리할 입고 요청들의 ID 리스트
     * @return 완료 처리가 성공하면 true, 실패하면 false
     */
    @Override
    public boolean completeInboundRequests(List<Integer> inboundIds) {
        return inboundMapper.completeList(inboundIds) > 0;
    }

    /**
     * 특정 입고 데이터를 삭제하는 메서드
     *
     * @param inboundId 삭제할 입고 데이터의 ID
     * @return 삭제가 성공하면 true, 실패하면 false
     */
    @Override
    public boolean remove(int inboundId) {
        return inboundMapper.delete(inboundId) > 0;
    }

    /**
     * 특정 입고 데이터를 조회하는 메서드
     *
     * @param inboundId 조회할 입고 데이터의 ID
     * @return 조회된 InboundDTO 객체를 Optional로 반환
     */
    @Override
    public Optional<InboundDTO> get(int inboundId) {
        Optional<Inbound> inbound = inboundMapper.selectOne(inboundId);
        return inbound.map(entity -> modelMapper.map(entity, InboundDTO.class));
    }

    /**
     * 모든 입고 데이터를 조회하는 메서드
     *
     * @return 조회된 InboundDTO 객체 리스트
     */

    @Override
    public PageResponseDTO<InboundDTO> getList(PageRequestDTO<InboundSearchCond> pageRequestDTO) {
        List<Inbound> inbounds = inboundMapper.selectAllList(pageRequestDTO);

        int total = inboundMapper.getCount(pageRequestDTO.getSearchCond());

        List<InboundDTO> dtoList = inbounds.stream()
            .map(InboundDTO::fromEntity)
            .toList();

        return PageResponseDTO.<InboundDTO>withAll()
            .pageRequestDTO(pageRequestDTO)
            .dtoList(dtoList)
            .total(total)
            .build();
    }




    /**
     * 특정 입고 요청 리스트를 삭제하는 메서드
     *
     * @param inboundIds 삭제할 입고 요청들의 ID 리스트
     */
    @Override
    public void removeList(List<Integer> inboundIds) {
        inboundMapper.deleteList(inboundIds);
    }
}
