package org.nowstart.study.data.type;

import lombok.Getter;

@Getter
public enum SearchType {

    ID("id"),
    CONTENT("content"),
    UNDEFINED("");

    private final String code;

    SearchType(String code) {
        this.code = code;
    }
}
