package org.nowstart.study.data.type;

import lombok.Getter;

@Getter
public enum ExceptionType {
    //bindingException
    NOT_BLANK("5000", "공백일 수 없음"),
    SIZE("5001", "사이즈가 잘못됨"),
    VALID_ENUM("5001", "파라미터 형식이 잘못됨"),

    //customException
    SECURITY_EXCEPTION("4003", "인증/인가 잘못됨"),
    NO_SUCH_ELEMENT_EXCEPTION("8001", "DB값이 없음"),
    USERNAME_NOT_FOUND_EXCEPTION("8001", "DB값이 없음"),
    DUPLICATE_REQUEST_EXCEPTION("8002", "키 값 중복"),

    //etc
    UNDEFINED("9999", "기타 에러");

    private final String flag;
    private final String message;

    ExceptionType(String flag, String message) {
        this.flag = flag;
        this.message = message;
    }
}
