package com.jionghong.scraper.utils;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Jionghong
 * @since 2020/9/25
 */
@Getter
@ToString
public enum ResultCodeEnum {

    SUCCESS(true, 20000,"success"),
    UNKNOWN_REASON(false, 20001, "unknown error");



    private Boolean success;

    private Integer code;

    private String message;

    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
