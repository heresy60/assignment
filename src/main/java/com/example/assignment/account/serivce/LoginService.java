package com.example.assignment.account.serivce;

import com.example.assignment.account.controller.request.LoginRequest;
import com.example.assignment.account.controller.response.LoginResponse;
import com.example.assignment.account.entity.Account;
import com.example.assignment.account.repository.AccountRepository;
import com.example.assignment.global.exception.NotFoundException;
import com.example.assignment.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Transactional
    public LoginResponse login(LoginRequest request) {


        Account account = repository.findByEmail(request.email()).orElseThrow(() -> new NotFoundException("가입한 적이 없거나 이메일 또는 비밀번호를 잘못 입력하셨습니다."));

        if (!passwordEncoder.matches(request.password(), account.getPassword())) {
            throw new NotFoundException("가입한 적이 없거나 이메일 또는 비밀번호를 잘못 입력하셨습니다.");
        }

        String token = tokenService.generateLoginToken(account.getId());

        return new LoginResponse(token);
    }
}
