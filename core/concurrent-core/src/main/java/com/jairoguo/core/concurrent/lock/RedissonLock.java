package com.jairoguo.core.concurrent.lock;

import com.jairoguo.core.concurrent.config.RedissonConfig;
import com.jairoguo.infra.concurrent.lock.BaseLock;
import java.util.concurrent.TimeUnit;
import org.redisson.Redisson;
import org.redisson.api.RLock;

/**
 * @author Jairo
 * @since 2023/7/14
 */
public class RedissonLock extends BaseLock {

  private static final Redisson redisson = RedissonConfig.getRedisson();


  @Override
  protected boolean acquire(String lockName, long waitTime, long expireTime, TimeUnit unit) {
    RLock lock = redisson.getLock(lockName);
    lock.lock(expireTime, TimeUnit.SECONDS);
    return true;
  }

  @Override
  protected boolean tryAcquire(String lockName, long waitTime, long expireTime, TimeUnit unit) {
    RLock lock = redisson.getLock(lockName);
    try {
      return lock.tryLock(waitTime, expireTime, unit);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      return false;
    }
  }

  @Override
  protected boolean release(String lockName) {
    RLock lock = redisson.getLock(lockName);
    lock.unlock();
    return false;
  }
}
