package org.nowstart.study.domain.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString
@AllArgsConstructor
@JsonRootName("result")
public class CommResponseVo {
    @Builder.Default
    @JsonProperty("flag")
    private String flag = "0000";

    @Builder.Default
    @JsonProperty("message")
    private String message = "성공";
}
