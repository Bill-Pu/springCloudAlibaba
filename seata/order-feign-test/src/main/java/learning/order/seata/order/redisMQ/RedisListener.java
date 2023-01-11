package learning.order.seata.order.redisMQ;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author ：Puyb
 * @date ：Created in 2023/1/5 20:34
 * @description：
 */
@Component
public class RedisListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println(message.getBody());
        System.out.println(new String(bytes));
    }
}
