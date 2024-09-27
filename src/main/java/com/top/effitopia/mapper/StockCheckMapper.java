package com.top.effitopia.mapper;

import com.top.effitopia.domain.StockCheck;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 재고실사 Mapper
 */
@Mapper
public interface StockCheckMapper {

    /**
     * 재고실사 등록
     * @param stockCheck
     */
    void insertStockCheck(StockCheck stockCheck);

    /**
     * 재고실사 수정
     * @param stockCheck
     */
    void updateStockCheck(StockCheck stockCheck);

    /**
     * 재고실사 삭제
     * @param: 삭제할 ID
     */
    void deleteStockCheck(int no);

    /**
     * 재고실사 리스트 조회
     * @return
     */
    List<StockCheck> selectListStockCheck();

    /**
     * 재고실사 하나 조회
     * @param: 조회할 ID
     * @return
     */
    Optional<StockCheck> selectOneStockCheck(@Param("id")int id);

    /**
     * 재고실사 여러개 등록
     */
    void insertListStockCheck(List<StockCheck> stockCheckList);

    /**
     * 재고실사 여러개 수정
     * @param stockCheckList
     */
    void updateListStockCheck(List<StockCheck> stockCheckList);

    /**
     * 재고실사 여러개 삭제
     * @param longList
     */
    void	deleteListStockCheck(List<Integer> longList);

}
