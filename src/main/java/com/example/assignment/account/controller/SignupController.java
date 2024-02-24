package com.example.assignment.account.controller;

import com.example.assignment.account.controller.request.SignupRequest;
import com.example.assignment.account.serivce.SignupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원 가입 관련 관련")
@RequestMapping("/api")
public class SignupController {

    private final SignupService service;

    @PostMapping("/signup")
    @Operation(summary = "회원 가입", description = "회원가입을 진행하는 API 입니다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "정상적으로 위탁 도서정보가 등록되었습니다.")
            })
    public ResponseEntity<Object> signup(@Validated @RequestBody SignupRequest request) {

        service.signup(request);

        return ResponseEntity.status(201).build();
    }
}