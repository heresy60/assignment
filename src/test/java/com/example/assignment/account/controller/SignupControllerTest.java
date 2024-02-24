package com.example.assignment.account.controller;

import com.example.assignment.account.controller.request.SignupRequest;
import com.example.assignment.account.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("회원가입 관련 테스트")
public class SignupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository repository;

    @BeforeEach
    void init() {
        repository.deleteAll();
    }


    @Test
    @DisplayName("회원가입")
    void signup() throws Exception {
        SignupRequest testRequest = new SignupRequest(
                "username",
                "email@example.com",
                "010-4756-7890",
                "Pass12"
        );

        ResultActions result = mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testRequest)));

        result.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("이메일 정합성 실패")
    void testSignupFailure() throws Exception {
        SignupRequest testRequest = new SignupRequest(
                "username",
                "email@examplewcom",
                "010-4756-7890",
                "Pass123!"
        );

        ResultActions result = mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testRequest)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("비밀번호 정합성 실패")
    void testSignupPasswordFailure() throws Exception {
        SignupRequest testRequest = new SignupRequest(
                "username",
                "email@example.com",
                "010-4756-7890",
                "invalidpassword"
        );

        ResultActions result = mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testRequest)));

        result.andExpect(status().isBadRequest());
    }


}