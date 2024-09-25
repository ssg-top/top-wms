package com.top.effitopia.domain;

import lombok.*;

import java.time.LocalDate;

/**
 * 재고
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    private int ID;                     //재고ID
    private int stockAmount;            //재고수량
    private LocalDate manufacturingDate;//제조일자
    private LocalDate expirationDate;   //생성일자
    private LocalDate regDate;          //생성일자
    private LocalDate modDate;          //변동일자
    private Member member;              //회원정보
    private int	cellID;                 //창고세부구역ID
    private Product	productID;          //상품정보
}
