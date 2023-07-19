package com.jairoguo.core.concurrent.config;


import com.jairoguo.core.concurrent.lock.ConcurrentHashMapLock;
import com.jairoguo.core.concurrent.lock.RedisLock;
import com.jairoguo.core.concurrent.lock.RedissonLock;
import com.jairoguo.infra.concurrent.lock.Lock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Jairo
 * @since 2023/7/14
 */
@Configuration
public class LockConfig {

  @Bean(name = "concurrentHashMap")
  public Lock concurrentHashMapLock() {
    return new ConcurrentHashMapLock();
  }

  @Bean(name = "redis")
  public Lock redisLock() {
    return new RedisLock(new RedisTemplate<>());
  }

  @Bean(name = "redisson")
  public Lock redissonLock() {
    return new RedissonLock();
  }
}
