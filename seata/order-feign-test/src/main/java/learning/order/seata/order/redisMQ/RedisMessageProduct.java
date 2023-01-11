package learning.order.seata.order.redisMQ;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：Puyb
 * @date ：Created in 2023/1/5 20:23
 * @description：
 */
@RestController
@RequestMapping("redis")
public class RedisMessageProduct {

    @Resource
    RedisTemplate redisTemplate;
    @GetMapping("sendMessage")
    public void sendMessage(String message) {
        redisTemplate.convertAndSend("topic1",new String("消息一条"));
    }
}
