package org.nowstart.study.config;

import java.util.StringJoiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.nowstart.study.data.type.ExceptionType;
import org.nowstart.study.data.vo.response.CommResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    private String toUpperSnakeCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        result.append(Character.toUpperCase(input.charAt(0)));

        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                result.append('_').append(Character.toUpperCase(currentChar));
            } else {
                result.append(Character.toUpperCase(currentChar));
            }
        }

        return result.toString();
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<CommResponseVo> handleBindException(BindException ex) {
        log.error(ex.getMessage(), ex);

        ExceptionType exceptionType;
        StringJoiner message;

        try {
            exceptionType = ExceptionType.valueOf(toUpperSnakeCase(ex.getFieldErrors().get(0).getCode()));
            message = new StringJoiner(" : ");
            message.add(ex.getFieldErrors().get(0).getField());
            message.add(exceptionType.getMessage());
        } catch (Exception e) {
            exceptionType = ExceptionType.UNDEFINED;
            message = new StringJoiner(" : ");
            message.add(ex.getFieldErrors().get(0).getField());
            message.add(ex.getFieldErrors().get(0).getDefaultMessage());
            message.add("현재 ExceptionType 정의되지 않았습니다.");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommResponseVo.builder()
                        .flag(exceptionType.getFlag())
                        .message(message.toString())
                        .build());

    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<CommResponseVo> handleThrowable(Throwable th) {
        log.error(th.getMessage(), th);

        ExceptionType exceptionType;
        try {
            exceptionType = ExceptionType.valueOf(toUpperSnakeCase(th.getClass().getSimpleName()));
        } catch (Exception e) {
            exceptionType = ExceptionType.UNDEFINED;
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommResponseVo.builder()
                    .flag(exceptionType.getFlag())
                    .message(StringUtils.defaultIfEmpty(th.getMessage(), exceptionType.getMessage()))
                        .build());

    }
}