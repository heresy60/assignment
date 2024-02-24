package com.example.assignment.global.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("요청하신 정보에 대해서 찾을 수 없습니다.");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
