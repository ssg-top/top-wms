package com.top.effitopia.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MailAuthDTO {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotNull
    @Pattern(regexp = "^[0-9]{6}$", message = "인증 번호는 6자리 숫자입니다.")
    private String code;
}
