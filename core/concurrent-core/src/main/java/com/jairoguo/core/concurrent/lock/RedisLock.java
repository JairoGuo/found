package com.jairoguo.core.concurrent.lock;

import com.jairoguo.infra.concurrent.lock.BaseLock;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

/**
 * @author Jairo
 * @since 2023/7/14
 */
public class RedisLock extends BaseLock {

  private final RedisTemplate<String, Object> redisTemplate;

  public RedisLock(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  protected boolean acquire(String lockName, long waitTime, long expireTime, TimeUnit unit) {
    return tryAcquire(lockName, waitTime, expireTime, unit);
  }

  @Override
  protected boolean tryAcquire(String lockName, long waitTime, long expireTime, TimeUnit unit) {

    Long result =
        execute(
            "lock.lua",
            Collections.singletonList(lockName),
            Long.class,
            Thread.currentThread().getId(),
            unit.convert(expireTime, unit));
    return result != null && result.intValue() == 1;
  }

  @Override
  protected boolean release(String lockName) {
    execute(
        "unlock.lua",
        Collections.singletonList(lockName),
        Long.class,
        Thread.currentThread().getId());
    return true;
  }

  private <T> T execute(String file, List<String> keys, Class<T> clazz, Object... args) {

    DefaultRedisScript<T> redisScript = new DefaultRedisScript<>();
    redisScript.setLocation(new ClassPathResource(file));
    redisScript.setResultType(clazz);

    return redisTemplate.execute(redisScript, keys, args);
  }
}
