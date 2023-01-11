package learning.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author ：Puyb
 * @date ：Created in 2022/12/23 16:48
 * @description：
 */
//@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){

        return Logger.Level.FULL;
    }
}
