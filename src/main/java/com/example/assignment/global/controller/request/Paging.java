package com.example.assignment.global.controller.request;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "페이징 요청 클래스")
@Hidden
public class Paging {

    @Min(value = 1, message = "최소 1 이상 이여야 합니다.")
    private Long page = 1L;

    @Max(value = 50, message = "최대 표기 갯수는 50 입니다.")
    private Long size = 20L;

    public PageRequest parse() {
        return PageRequest.of(Math.toIntExact(page-1), Math.toIntExact(size));
    }
}
