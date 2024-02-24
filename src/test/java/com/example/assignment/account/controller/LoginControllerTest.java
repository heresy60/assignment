package com.example.assignment.account.controller;

import com.example.assignment.account.controller.request.LoginRequest;
import com.example.assignment.account.controller.request.SignupRequest;
import com.example.assignment.account.entity.Account;
import com.example.assignment.account.repository.AccountRepository;
import com.example.assignment.security.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("로그인 관련 테스트")
@ActiveProfiles("test")
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @BeforeEach
    void beforeEach() {
        accountRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인")
    void login() throws Exception {

        createUser();
        LoginRequest loginRequest = new LoginRequest("ww@gmail.com", "1q2w3e4r!");

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(loginRequest)));


        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.accessToken").isNotEmpty());
    }

    @Test
    @DisplayName("로그인 실패")
    void loginFail() throws Exception {

        createUser();

        LoginRequest passwordWrong = new LoginRequest("ww@gmail.com", "1q2w3e42r1!");
        LoginRequest phoneNumberWrong = new LoginRequest("www@gmail.com", "1q2w3e4r!");

        ResultActions result1 = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(passwordWrong)));

        ResultActions result2 = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(phoneNumberWrong)));

        result1.andExpect(status().isNotFound());
        result1.andExpect(jsonPath("$.description").value("가입한 적이 없거나 이메일 또는 비밀번호를 잘못 입력하셨습니다."));

        result2.andExpect(status().isNotFound());
        result2.andExpect(jsonPath("$.description").value("가입한 적이 없거나 이메일 또는 비밀번호를 잘못 입력하셨습니다."));
    }

    private void createUser() {
        accountRepository.save(new Account(new SignupRequest("홍길동", "ww@gmail.com", "010-1111-1111", "1q2w3e4r!")));
    }
}