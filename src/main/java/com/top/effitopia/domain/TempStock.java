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
    private int	ID;	                    //임시재고ID
    private int	changeAmount;	        //변동 수량
    private LocalDate manufacturingDate;//제조일자
    private LocalDate expirationDate;   //유통기한
    private LocalDate regDate;          //재고생성일
    private Member member;              //회원정보
    private int	cellID;                 //창고세부구역ID
    private Product	product;            //상품정보
    private Stock stock;                //재고정보
}
