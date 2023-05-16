package org.nowstart.study.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.nowstart.study.domain.type.ExceptionType;
import org.nowstart.study.domain.vo.response.CommResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.StringJoiner;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<CommResponseVo> handleBindException(BindException ex) {
        log.error(ex.getMessage(), ex);

        ExceptionType exceptionType = ExceptionType.valueOf(ex.getFieldErrors().get(0).getCode());
        StringJoiner message = new StringJoiner(" , ");
        ex.getFieldErrors().stream().map(e -> e.getField() + " : " + e.getDefaultMessage()).forEach(message::add);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommResponseVo.builder()
                        .flag(exceptionType.getFlag())
                        .message(message.toString())
                        .build());

    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<CommResponseVo> handleThrowable(Throwable th) {
        log.error(th.getMessage(), th);
        ExceptionType exceptionType = ExceptionType.valueOf(th.getClass().getName());

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommResponseVo.builder()
                        .flag(exceptionType.getFlag())
                        .message(exceptionType.getMessage())
                        .build());

    }
}