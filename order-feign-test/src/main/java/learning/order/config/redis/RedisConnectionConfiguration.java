package learning.order.config.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import learning.order.config.CommonDateConstants;
import learning.order.config.ObjectMapperUtil;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@AutoConfigureAfter(RedisAutoConfiguration.class)
@Configuration(proxyBeanMethods = true)
@ConditionalOnClass({ JedisConnection.class})
public class RedisConnectionConfiguration {

    private final static ObjectMapper objectMapper = ObjectMapperUtil.instance.getAdvanceObjectMapper();
    
	@Bean
	public RedisMessageListenerContainer jedisListenerContainer(RedisConnectionFactory jedisConnectionFactory) {
		RedisMessageListenerContainer jedisListenerContainer = new RedisMessageListenerContainer();
		jedisListenerContainer.setConnectionFactory(jedisConnectionFactory);
		return jedisListenerContainer;
	}
	
	@ConditionalOnMissingBean(LocalDateSerializer.class)
	@Bean
    public LocalDateSerializer localDateSerializer() {
        return new LocalDateSerializer(CommonDateConstants.dateTimeFormatterYYYYMMDD);
    }
	
	@ConditionalOnMissingBean(LocalDateTimeSerializer.class)
    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(CommonDateConstants.dateTimeFormatter);
    }

	@ConditionalOnMissingBean(JsonCommonSerializer.class)
	@Bean
	public JsonCommonSerializer jsonRedisSerializer() {
		return new JsonCommonSerializer();
	}

	@Bean
	public RedisTemplate<String, byte[]> redisByteTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, byte[]> template = new RedisTemplate<String, byte[]>();
		template.setConnectionFactory(factory);
		
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的key也采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance , DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		// value
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// value
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
	
	/**
     * RedisTemplate配置
     *
     * @param	
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
