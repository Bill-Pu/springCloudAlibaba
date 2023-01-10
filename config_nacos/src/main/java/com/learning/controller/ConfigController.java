package com.learning.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Puyb
 * @date ：Created in 2023/1/6 17:37
 * @description：
 */
@RestController
@RequestMapping("test")
@RefreshScope
public class ConfigController {
    @Value("${user.name}")
    public String userName;
    @GetMapping("userName")
    public String getUserName() {
        return userName;
    }
}
