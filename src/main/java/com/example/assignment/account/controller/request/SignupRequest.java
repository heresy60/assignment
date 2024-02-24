package com.example.assignment.account.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record SignupRequest(
        @NotBlank(message = "회원 이름은 필수입니다.")
        String username,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식을 입력해 주세요.", regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
        String email,

        @NotBlank(message = "휴대폰 번호는 필수입니다.")
        @Pattern(regexp = "[0-9]{3}-[0-9]{3,4}-[0-9]{4}", message = "올바른 휴대폰 번호를 입력해 주세요.")
        String phoneNumber,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 6, max = 10, message = "비밀번호는 최소 6자 이상, 최대 10자 이하를 사용해야 합니다.")
        @Pattern(regexp = "^(?=(.*[a-z])+)(?=(.*[A-Z])+)(?=(.*\\d)+).{6,10}$",
                message = "비밀번호는 영문 소문자, 대문자, 숫자 중 최소 두 가지 이상 조합이 필요합니다.")
        String password
) {}