package org.nowstart.study.domain.type;

import lombok.Getter;

@Getter
public enum ExceptionType {
    //bindingException
    NOT_BLANK("5000", "공백일 수 없습니다."),
    SIZE("5001", "사이즈가 너무 큽니다."),

    //customException
    NO_SUCH_ELEMENT_EXCEPTION("8001", "DB값이 없음"),
    NOT_FOUND_EXCEPTION("8001", "DB값이 없음"),

    //etc
    UNDEFINED("9999", "기타 에러");

    private final String flag;
    private final String message;

    ExceptionType(String flag, String message) {
        this.flag = flag;
        this.message = message;
    }
}
