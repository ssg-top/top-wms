package com.top.effitopia.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATE_USERNAME("이미 존재하는 아이디 입니다."),
    DUPLICATE_EMAIL("이미 존재하는 이메일 입니다."),
    NOT_EXISTS_MEMBER("존재하지 않는 회원입니다."),
    INVALID_BUSINESS_NUMBER("유효하지 않은 사업자 번호 입니다.");

    private final String message;

}
