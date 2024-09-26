package com.top.effitopia.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATE_USERNAME("이미 존재하는 아이디 입니다."),
    DUPLICATE_EMAIL("이미 존재하는 이메일 입니다."),
    NOT_EXISTS_MEMBER("존재하지 않는 회원입니다."),
    INVALID_BUSINESS_NUMBER("유효하지 않은 사업자 번호 입니다."),
    NOT_MATCHES_PASSWORD("비밀번호가 일치하지 않습니다."),
    NOT_MATCHES_NEW_PASSWORD_CONFIRM("새 비밀번호와 비밀번호 확인이 일치하지 않습니다."),
    DUPLICATE_PASSWORD("현재 비밀번호와 동일합니다.");

    private final String message;

}
