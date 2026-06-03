package com.example.jpadoc.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "M001", "회원을 찾을 수 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "M002", "이미 사용 중인 이메일입니다."),

    // Common
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "C001", "잘못된 입력값입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
