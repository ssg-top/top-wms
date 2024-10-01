package com.top.effitopia.domain;

import lombok.*;

import java.time.LocalDate;


/**
 * 임시 재고
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TempStock {
    private int	id;	                    //임시재고ID
    private int	changeAmount;	        //변동 수량
    private LocalDate manufacturingDate;//제조일자
    private LocalDate expirationDate;   //유통기한
    private LocalDate regDate;          //재고생성일
    private boolean tempStockState;     //임시재고 상태
    private Member member;              //회원정보
    private Cell	cell;               //창고세부구역
    private Product	product;            //상품정보

}
