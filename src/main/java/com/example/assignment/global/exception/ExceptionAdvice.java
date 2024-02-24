package com.example.assignment.global.exception;

import com.example.assignment.global.exception.response.BaseErrorResponse;
import com.example.assignment.global.exception.response.MethodArgumentsValidErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseErrorResponse httpMessageNotReadableException(HttpMessageNotReadableException e) {


        if (e.getCause() instanceof InvalidFormatException cause) {

            String fieldName = cause.getPath().get(0).getFieldName();
            return new BaseErrorResponse( fieldName + " 필드 값이 잘못 된 자료형입니다.");
        } else {

            log.error(e.getMessage());
            return new BaseErrorResponse("서버에서 처리 중 에러가 발생했습니다.");
        }
    }

    /**
     * MethodArgumentNotValidException 에러 처리하기 위한 메소드
     *
     * @param errors @{@link jakarta.validation.Valid}의해 발생한 에러 목록
     * @return 에러 메시지를 표기해서 지정한 반환 값
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public MethodArgumentsValidErrorResponse methodArgumentNotValidException(Errors errors) {

        return new MethodArgumentsValidErrorResponse(
                "요청 값을 검증 중 오류가 발생했습니다.",
                errors.getFieldErrors().stream()
                        .map(
                                fieldError -> {

                                    String message = Objects.requireNonNull(fieldError.getCode()).contentEquals("typeMismatch") ? "잘못 된 자료형입니다." : fieldError.getDefaultMessage();
                                    return new MethodArgumentsValidErrorResponse.DetailMessage(message, fieldError.getField());
                                }
                        ).toList());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public BaseErrorResponse badRequestException(BadRequestException exception) {

        return new BaseErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public BaseErrorResponse notFoundException(NotFoundException exception) {

        return new BaseErrorResponse(exception.getMessage());
    }
    
    /**
     * 잡지 못한 에러 처리를 위한 메소드
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseErrorResponse exception(Exception e) {

        log.error(e.getMessage());
        return new BaseErrorResponse("서버에서 처리 중 에러가 발생했습니다.");
    }
}
