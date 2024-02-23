package com.example.assignment.global.exception.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Valid 에러 처리 객체")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MethodArgumentsValidErrorResponse extends BaseErrorResponse {

    @Schema(description = "요청 값 중 문제가 있는 항목에 대한 정보")
    List<DetailMessage> messages;

    public MethodArgumentsValidErrorResponse(String description, List<DetailMessage> messages) {
        super(description);
        this.messages = messages;
    }

    @Getter
    @AllArgsConstructor
    public static class DetailMessage {

        @Schema(description = "메시지", example = "이름은 필수입니다.")
        private String message;
        @Schema(description = "에러난 필드", example = "name")
        private String field;
    }
}
