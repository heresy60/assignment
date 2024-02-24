package com.example.assignment.account.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "로그인 요청 객체")
public record LoginRequest(

        @Schema(description = "이메일", example = "aa@gmail.com")
        @NotBlank(message = "이메일은 필수입니다.")
        String email,

        @Schema(description = "비밀번호", example = "1q2w3e4r")
        @NotBlank(message = "비밀번호는 필수입니다.")
        String password
) {
}
