package com.example.assignment.account.controller;

import com.example.assignment.account.controller.request.LoginRequest;
import com.example.assignment.account.controller.response.LoginResponse;
import com.example.assignment.account.serivce.LoginService;
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
@RequestMapping("/api")
@Tag(name = "로그인 관련")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인을 진행할 수 있는 API 입니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "정상적으로 로그인 성공 시")
            })
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest request) {

        return ResponseEntity.ok((loginService.login(request)));
    }

}
