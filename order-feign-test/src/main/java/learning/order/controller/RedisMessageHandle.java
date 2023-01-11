package learning.order.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ：Puyb
 * @date ：Created in 2023/1/5 20:37
 * @description：
 */
@Component
public class RedisMessageHandle {
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    RedisListener redisListener;
    @Resource
    RedisConnectionFactory redisConnectionFactory;
    @Bean
    public RedisMessageListenerContainer initRedisListenerContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        ChannelTopic topic1 = new ChannelTopic("topic1");
        container.addMessageListener(redisListener, topic1);
        return container;
    }
}
