package cc.config;

import config.RedisCacheConfig;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class DetailsConfiguration extends RedisCacheConfig {

    @Bean
    @Override
    protected CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return super.cacheManager(redisConnectionFactory);
    }
}
