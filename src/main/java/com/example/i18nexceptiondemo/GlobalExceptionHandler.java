package com.example.i18nexceptiondemo;

import app.frame.common.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Objects;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource      messageSource;
    @Autowired
    private HttpServletRequest request;

    /**
     * 拦截自定义的异常
     */
    @ExceptionHandler(AppException.class)
    public R handleAppException(AppException e) {
        log.warn(e.getMessage(), e);
        return R.of(e.getCode(), getLocaleMessage(e.getCode(), e.getMessage(), e.getParams()));
    }

    /**
     * 处理其他抛出的异常
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.warn(e.getMessage(), e);
        return R.of("999999", getLocaleMessage("999999", "unknown exception", null));
    }

    /**
     * 获取国际化异常信息
     */
    private String getLocaleMessage(String code, String defaultMsg, Object[] params) {
        String language = request.getParameter("lang");
        Locale locale = Objects.nonNull(language) ? new Locale(language) : Locale.getDefault();
        try {
            return messageSource.getMessage(code, params, locale);
        } catch (Exception e) {
            log.warn("本地化异常消息发生异常: {}, {}", code, params);
            return defaultMsg;
        }
    }
}
