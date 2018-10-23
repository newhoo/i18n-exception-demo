package com.example.i18nexceptiondemo;

import app.frame.common.response.R;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class I18nExceptionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(I18nExceptionDemoApplication.class, args);
    }

    @RequestMapping("/demo")
    public R demo(String msg) {
        // 模拟带占位符的A异常
        if ("a".equals(msg)) {
            throw new AppException("010101", "haha");
        }
        // 模拟不带占位符的B异常
        if ("b".equals(msg)) {
            throw new AppException("010102");
        }
        // 模拟其他异常
        if ("c".equals(msg)) {
            throw new RuntimeException();
        }
        return R.ok("hello " + msg);
    }
}
