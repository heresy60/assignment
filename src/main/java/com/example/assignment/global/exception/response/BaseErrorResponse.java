package com.example.assignment.global.exception.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "에러 기본 객체")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class BaseErrorResponse {

    @Schema(description = "설명", example = "잘못 된 정보입니다.")
    private String description;
}
