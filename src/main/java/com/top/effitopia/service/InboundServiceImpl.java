package com.top.effitopia.service;

import com.top.effitopia.domain.Inbound;
import com.top.effitopia.dto.InboundDTO;
import com.top.effitopia.mapper.InboundMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InboundServiceImpl implements InboundService {

    private final InboundMapper inboundMapper;
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * InboundDTO 를 받아 새로운 입고 데이터를 생성하는 메서드
     *
     * @param inboundDTO InboundDTO 객체에 저장
     * @return 성공적으로 데이터가 들어가면 true, 아니면 false
     */
    @Override
    public boolean save(InboundDTO inboundDTO) {
        Inbound inbound = dtoToEntity(inboundDTO);
        return inboundMapper.insert(inbound) > 0;
    }


    /**
     * 특정 입고 데이터를 수정하는 메서드
     *
     * @param inboundDTO 수정할 입고 데이터
     * @return 수정 성공하면 true, 실패하면 false
     */
    @Override
    public boolean modify(InboundDTO inboundDTO) {
        return false;
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

    @Override
    public Optional<InboundDTO> get(int inboundId) {
        return Optional.empty();
    }

    @Override
    public List<InboundDTO> getList() {
        return null;
    }

    @Override
    public void saveList(List<InboundDTO> inboundDTOList) {

    }

    @Override
    public void modifyList(List<InboundDTO> inboundDTOList) {

    }

    @Override
    public void removeList(List<Integer> inboundIdList) {

    }


    /**
     * InboundDTO 를 Entity 로 변환
     *
     * @param inboundDTO InboundDTO 를 변환
     * @return 변환된 Inbound 엔티티 객체
     */
    private Inbound dtoToEntity(InboundDTO inboundDTO) {
        return Inbound.builder()
            .inboundId(inboundDTO.getInboundId())
/*            .member(inboundDTO.getMember())
            .warehouse(inboundDTO.getWarehouse())
            .product(inboundDTO.getProduct())  */
            .inboundRequestDate(inboundDTO.getInboundRequestDate())
            .inboundApprovedDate(inboundDTO.getInboundApprovedDate())
            .inboundExpectDate(inboundDTO.getInboundExpectDate())
            .inboundCompletedDate(inboundDTO.getInboundCompletedDate())
            .inboundStatus(inboundDTO.getInboundStatus())
            .build();
    }

    /**
     * Entity 를 InboundDTO 로 변환
     *
     * @param inbound Inbound(도메인)를 변환
     * @return 변환된 InboundDTO
     */
    private InboundDTO entityToDTO(Inbound inbound) {
        return InboundDTO.builder()
            .inboundId(inbound.getInboundId())
/*            .member(inbound.getMember())
            .warehouse(inbound.getWarehouse())
            .product(inbound.getProduct())  */
            .inboundRequestDate(inbound.getInboundRequestDate())
            .inboundApprovedDate(inbound.getInboundApprovedDate())
            .inboundExpectDate(inbound.getInboundExpectDate())
            .inboundCompletedDate(inbound.getInboundCompletedDate())
            .inboundStatus(inbound.getInboundStatus())
            .build();
    }

}
