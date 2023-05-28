package com.jairoguo.infra.common.lock;

import java.util.concurrent.TimeUnit;

/**
 * 用于实现方法引用传参接口
 *
 * @author Jairo
 */
public interface Lock {

  long EXPIRE_TIME = 60;

  void lock();

  void lock(String lockName);

  default boolean tryLock(String lockName) {
    return tryLock(lockName, 0);
  }

  default boolean tryLock(String lockName, long waitTime) {
    return tryLock(lockName, waitTime, EXPIRE_TIME);
  }

  default boolean tryLock(String lockName, long waitTime, long expireTime) {
    return tryLock(lockName, waitTime, expireTime, TimeUnit.SECONDS);
  }

  default boolean tryLock(String lockName, long waitTime, TimeUnit timeUnit) {
    return tryLock(lockName, waitTime, EXPIRE_TIME, timeUnit);
  }

  boolean tryLock(String lockName, long waitTime, long expireTime, TimeUnit unit);

  void unlock();

  void unlock(String lockName);
}
