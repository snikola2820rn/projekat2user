package com.rafrn2820.project2.UserService.exception;

import org.springframework.http.HttpStatus;

public class BannedUserException extends CustomException{
    public BannedUserException(String message) {
        super(message, ErrorCode.BANNED, HttpStatus.FORBIDDEN);
    }
}
