package com.example.assignment.account.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "로그인 응답 객체")
public record LoginResponse(

        @Schema(description = "토큰 값", example = "xxx.xxx.xxx")
        String accessToken
) {
}
