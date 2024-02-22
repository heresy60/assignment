package com.example.assignment.global.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageResponse<T>{

    @Schema(description = "총 데이터 수", example = "10")
    private long totalCount;

    @Schema(description = "데이터")
    private List<T> data;
}
