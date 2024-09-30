package com.top.effitopia.exception;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = InboundFutureDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureDate {

    /**
     * 유효성 검사가 실패할 경우 메시지
     */
    String message() default "입고 예정일은 현재 날짜로부터 최소 2일 뒤어야 합니다.";

    /**
     * 유효성 검사 그룹 설정
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int after() default 2;
}
