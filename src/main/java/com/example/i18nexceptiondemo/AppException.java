package com.example.i18nexceptiondemo;

import lombok.Getter;

/**
 * 自定义基础异常类
 */
@Getter
public class AppException extends RuntimeException {

    private String   code;
    private Object[] params;

    /**
     * 支持自定义错误码和提示信息的异常
     *
     * @param code 错误码
     */
    public AppException(String code, Object... params) {
        super(code);
        this.code = code;
        this.params = params;
    }
}
