package com.rafrn2820.project2.UserService.exception;

import org.springframework.http.HttpStatus;

public class WrongPasswordException extends CustomException{
    public WrongPasswordException(String message) {
        super(message, ErrorCode.WRONG_PASS, HttpStatus.UNAUTHORIZED);
    }
}
