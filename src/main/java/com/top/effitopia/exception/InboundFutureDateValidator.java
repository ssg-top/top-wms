package com.top.effitopia.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.time.LocalDate;

public class InboundFutureDateValidator implements ConstraintValidator<FutureDate, LocalDate> {

    private int after;

    @Override
    public void initialize(FutureDate constraintAnnotation) {
        this.after = constraintAnnotation.after();
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (date == null) {
            return true;
        }

        LocalDate now = LocalDate.now();
        return date.isAfter(now.plusDays(1));
    }
}
