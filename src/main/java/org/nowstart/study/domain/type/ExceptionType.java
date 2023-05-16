package org.nowstart.study.domain.type;

import lombok.Getter;

@Getter
public enum ExceptionType {
    NotBlank("5000", "공백일 수 없습니다."),
    Size("5001", "사이즈가 너무 큽니다."),
    UNDEFINED("9999", "기타 에러");

    private final String flag;
    private final String message;

    ExceptionType(String flag, String message) {
        this.flag = flag;
        this.message = message;
    }
}
