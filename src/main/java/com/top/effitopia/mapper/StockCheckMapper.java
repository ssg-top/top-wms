package com.top.effitopia.mapper;

import com.top.effitopia.domain.StockCheck;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 재고실사 Mapper
 */
@Mapper
public interface StockCheckMapper {

    /**
     * 재고실사 등록
     * @param stockCheck
     */
    void insertStockcheck(StockCheck stockCheck);

    /**
     * 재고실사 수정
     * @param stockCheck
     */
    void updateStockcheck(StockCheck stockCheck);

    /**
     * 재고실사 삭제
     * @param: 삭제할 ID
     */
    void	deleteStockcheck(int no);

    /**
     * 재고실사 리스트 조회
     * @return
     */
    List<StockCheck> selectListStockcheck();

    /**
     * 재고실사 하나 조회
     * @param: 조회할 ID
     * @return
     */
    StockCheck	selectOneStockcheck(int no);

    /**
     * 재고실사 여러개 등록
     */
    void insertListStockcheck(List<StockCheck> stockCheckList);

    /**
     * 재고실사 여러개 수정
     * @param stockCheckList
     */
    void updateListStockcheck(List<StockCheck> stockCheckList);

    /**
     * 재고실사 여러개 삭제
     * @param longList
     */
    void	deleteListStockcheck(List<Long> longList);

}
