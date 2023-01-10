package com.learning;

import jdk.nashorn.internal.ir.CallNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author ：Puyb
 * @date ：Created in 2023/1/6 16:00
 * @description：
 */
@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext run = SpringApplication.run(ConfigApplication.class, args);
        while(true) {
            String property = run.getEnvironment().getProperty("user.name");
            String age = run.getEnvironment().getProperty("user.age");
            System.out.println(property+"  "+ age);
            TimeUnit.SECONDS.sleep(1);
        }


    }
}
