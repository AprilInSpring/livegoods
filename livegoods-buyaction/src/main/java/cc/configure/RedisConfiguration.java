package cc.configure;

import config.RedisCacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

//redis配置类,定义value为object类型
@Configuration
public class RedisConfiguration extends RedisCacheConfig {

    @Bean
    @Override
    protected RedisTemplate<String, Object> redisObjectTemplate(RedisConnectionFactory redisConnectionFactory) {
        return super.redisObjectTemplate(redisConnectionFactory);
    }
}
